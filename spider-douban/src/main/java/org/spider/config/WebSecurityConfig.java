package org.spider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                // 登录api
                .antMatchers("/**/login").permitAll()
                // knife4j swagger
                // 静态资源文件
                .antMatchers("/*.ico", "/css/**", "/images/**", "/js/**", "/plugins/**", "/library/**", "/themes/**",
                        "/uploads/**").permitAll()
                .and()
                // 跨域支持
                .cors()
                .and()
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable();
        //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        //httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 跨域访问配置
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return rep -> {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.addAllowedOrigin("*");
            cfg.addAllowedHeader("*");
            cfg.addAllowedMethod("*");
            cfg.setAllowCredentials(true);
            return cfg;
        };
    }
}
