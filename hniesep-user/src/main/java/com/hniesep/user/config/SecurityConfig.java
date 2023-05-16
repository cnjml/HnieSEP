package com.hniesep.user.config;

import com.hniesep.framework.handler.security.AccessDeniedHandlerImpl;
import com.hniesep.framework.handler.security.AuthenticationEntryPointImpl;
import com.hniesep.user.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


/**
 * @author 吉铭炼
 */
@Configuration
@EnableGlobalAuthentication
public class SecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //不需要默认注销方法
                .logout().disable()
                //cors放开
                .cors().disable()
                // 基于 token，不需要 csrf
                .csrf().disable()
                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 下面开始设置权限
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        //通用放开
                        .requestMatchers("/mail/**").permitAll()
                        .requestMatchers("/board/**").permitAll()
                        //个别放开
                        .requestMatchers("/article/articleDetail/**").permitAll()
                        .requestMatchers("/article/popularArticles").anonymous()
                        .requestMatchers("/article/articleList/**").anonymous()
                        .requestMatchers("/comment/commentList/**").anonymous()
                        .requestMatchers("/account/register").anonymous()
                        .requestMatchers("/account/login").anonymous()
                        .requestMatchers("/account/existUsername/**").anonymous()
                        .requestMatchers("/account/existEmail/**").anonymous()
                        // 其他地址的访问均需验证权限
                        .anyRequest().authenticated()
                );
        //用户密码验证过滤器前加token过滤器，如果token有效则跳过用户密码验证过滤器
        http.addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //添加异常处理器
        http.exceptionHandling()
                //自定义禁止访问处理器
                .accessDeniedHandler(accessDeniedHandler)
                //自定义认证入口处理器
                .authenticationEntryPoint(authenticationEntryPoint);
        return http.build();
    }

    /**
     * 配置资源文件跨源访问(CORS)
     *
     * @return CorsConfigurationSource
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
