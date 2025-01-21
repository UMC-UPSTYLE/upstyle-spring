package com.upstyle.upstyle.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf

                        .ignoringRequestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/ootds/**", "/clothes/**", "/users/**", "/auth/**", "/login/**") // CSRF 비활성화 경로

                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/", "/home", "/signup", "/users/signup", "/css/**",
                                "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**"
                        ).permitAll() // Swagger 및 기타 공개 경로 허용

                        .requestMatchers("/ootds/**", "/clothes/**", "/users/**", "/auth/**", "/login/**").permitAll()

                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated() // 그 외 요청은 인증 필요
                )
                .formLogin(form -> form
                        .loginPage("/login") // 사용자 정의 로그인 페이지로 설정
                        .defaultSuccessUrl("/more_info", true)
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        //.defaultSuccessUrl("/more_info", true)
                        .authorizationEndpoint(authorization ->
                                authorization.baseUri("/oauth2/authorization") // OAuth 요청 엔드포인트
                        )
                        .redirectionEndpoint(redirection ->
                                redirection.baseUri("/login/oauth2/code/**") // Redirect 엔드포인트
                        )
                        .userInfoEndpoint(userInfo ->
                                userInfo.userService(customOAuth2UserService) // 사용자 정보 처리 서비스
                        )
                );

        return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}