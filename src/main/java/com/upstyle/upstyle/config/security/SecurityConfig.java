package com.upstyle.upstyle.config.security;

import com.upstyle.upstyle.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

    private final CustomOAuth2UserService customOAuth2UserService;
    private final TokenService tokenService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService,TokenService tokenService,
                          OAuth2SuccessHandler oAuth2SuccessHandler) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.tokenService = tokenService;
        this.oAuth2SuccessHandler = oAuth2SuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/ootds/**", "/clothes/**", "/users/**", "/auth/**", "/calendar/**", "/login/oauth2/code/google", "/login", "/signin/**", "/more_info") // CSRF 비활성화 경로


                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/", "/login", "/more_info", "/css/**",
                                "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**"
                        ).permitAll() // Swagger 및 기타 공개 경로 허용

                        .requestMatchers("/ootds/**", "/clothes/**", "/users/**", "/auth/**", "/calendar/**", "/login/oauth2/code/google", "/login", "/signin/**", "/more_info").permitAll()

                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated() // 그 외 요청은 인증 필요
                )
                // oauth2 설정
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // 사용자 정보 처리 서비스
                        )
                        .defaultSuccessUrl("/more_info", true)
                );

        return http.build();
    }

}