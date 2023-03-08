package org.raysvivi.blog.config;

import org.raysvivi.blog.filter.JwtAuthenticationTokenFilter;
import org.raysvivi.blog.handler.JwtAuthenticationEntryPoint;
import org.raysvivi.blog.utils.Md5PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final UserDetailsService customUserDetailsService;
    /**
     * 请求拦截器
     */
    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler,
                             @Qualifier("CustomUserDetailsService") UserDetailsService customUserDetailsService,
                             JwtAuthenticationTokenFilter authenticationTokenFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationTokenFilter = authenticationTokenFilter;
    }
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
                .antMatchers("/api/tourist/**","/api/constants/**").permitAll()
                .and()
                // 跨域支持
                .cors()
                .and()
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                //禁用iframe同源策略
                .and().headers().frameOptions().disable();

        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
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

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.customUserDetailsService)
                // 密码编码器
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 装载密码编码器
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用自定义MD5编码器
        return new Md5PasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
