package org.raysvivi.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.raysvivi.blog.model.user.UserSecurityInfo;
import org.raysvivi.blog.utils.JwtUtil;
import org.spider.common.constant.ExceptionConstant;
import org.spider.common.exception.ViviException;
import org.spider.common.util.Md5Util;
import org.spider.common.util.ResponseUtil;
import org.spider.model.tuple.Tuple2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.spider.common.util.ResponseData;
import org.raysvivi.blog.model.log.LoginParam;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AdminService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AdminService(AuthenticationManager authenticationManager,JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    public ResponseData<?> checkLogin(LoginParam param) {
        param.setPassword(Md5Util.encode(param.getPassword()));
        //用户验证
        Authentication authentication = authenticate(param.getUsername(), param.getPassword());
        if (authentication == null) {
            return ResponseUtil.error(ExceptionConstant.Error.code);
        }
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserSecurityInfo userDetail = (UserSecurityInfo) authentication.getPrincipal();
        //生成token、过期时间
        Tuple2<String, Long> tp = jwtUtil.generateToken(userDetail);
        return this.assembleResult(tp);
    }

    private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码
            // 如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            log.error(e.toString());
            throw new ViviException(ExceptionConstant.Error.code);
        }
    }

    private ResponseData<?> assembleResult(Tuple2<String, Long> tp) {
        if (tp == null) {
            return ResponseUtil.error(ExceptionConstant.Error.code);
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("token", tp.item1);
        map.put("expiration", tp.item2);
        return ResponseUtil.ok(map);
    }
}
