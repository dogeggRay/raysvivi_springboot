package org.raysvivi.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.raysvivi.blog.model.user.UserSecurityInfo;
import org.spider.model.tuple.Tuple2;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("jwtUtil")
public class JwtUtil {
    /**
     * token 失效时间，单位秒
     */
    private final Long tokenExpiration = 86400L;
    private static final String ACCOUNT_NAME = "AccountName";
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private final String secret = "Raysvivi@ld";
    private final Map<String, String> tokenMap = new HashMap<>();

    JwtUtil() {
        //TODO 将token存入redis,服务启动时从redis读取
//        tokenMap.put("1_admin", tmpToken);
    }
    /**
     * 根据用户信息生成token、有效期(秒)
     *
     * @param userInfo
     * @return
     */
    public Tuple2<String, Long> generateToken(UserSecurityInfo userInfo) {
        String token = generateToken(userInfo, tokenExpiration);
        tokenMap.put(userInfo.getKey(), token);
        return new Tuple2<>(token, tokenExpiration);
    }

    private String generateToken(UserSecurityInfo userDetail, long expiration) {
        Map<String, Object> claims = generateClaims(userDetail);
        String username = userDetail.getName();
        String userId = String.valueOf(userDetail.getId());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setId(userId)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    private Map<String, Object> generateClaims(UserSecurityInfo userDetail) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(ACCOUNT_NAME, String.valueOf(userDetail.getName()));
        return claims;
    }

    public UserSecurityInfo getUserInfoFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        String username = claims.getSubject();
        //Integer actId = Integer.parseInt(claims.get(ACCOUNT_NAME).toString());

        return UserSecurityInfo.builder()
                .name(username)
                .build();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public boolean checkValidToken(String token, String key) {
        return tokenMap.containsKey(key) && tokenMap.get(key).equals(token);
    }

    /**
     * 生成token过期时间
     *
     * @param expiration
     * @return
     */
    private Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    /**
     * @param token
     * @return
     */
    public Boolean isAccessTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        if (expiration == null) {
            return true;
        }
        return expiration.before(new Date());
    }

    /**
     * 获取token过期时间
     *
     * @param token
     * @return
     */
    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
}
