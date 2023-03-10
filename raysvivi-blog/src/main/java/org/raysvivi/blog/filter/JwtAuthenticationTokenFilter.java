package org.raysvivi.blog.filter;

import org.apache.commons.lang3.StringUtils;
import org.raysvivi.blog.model.user.UserSecurityInfo;
import org.raysvivi.blog.utils.JwtUtil;
import org.spider.common.cache.CommonSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final String tokenHeader = "Authorization";
    private final String authTokenStart = "Raysvivi ";

    @Resource
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(this.tokenHeader);

        if (StringUtils.isNotEmpty(token) && token.startsWith(authTokenStart)) {
            token = token.substring(authTokenStart.length());
        } else {
            token = null;
        }
        if(request.getRequestURI().contains("/tourist")||request.getRequestURI().contains("/constants")){
            filterChain.doFilter(request, response);
            return;
        }
        // 去除登录接口中的token信息
        if (StringUtils.isNotEmpty(token) && (request.getRequestURI().contains("/api/admin/login"))) {
            token = "";
        }
        if (StringUtils.isNotEmpty(token) && jwtUtil.isAccessTokenExpired(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
        }

        CommonSession.clearCache();

        UserSecurityInfo userInfo = jwtUtil.getUserInfoFromToken(token);
        if (userInfo != null && jwtUtil.checkValidToken(token, userInfo.getKey()) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CommonSession.init(userInfo.getName(), token);
        }
        filterChain.doFilter(request, response);
    }
}
