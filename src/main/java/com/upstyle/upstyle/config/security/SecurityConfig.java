package com.upstyle.upstyle.config.security;

import com.upstyle.upstyle.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final TokenService tokenService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 추가
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/users/**", "/auth/**", "/ootds/**", "/clothes/**", "/closets/**", "/votes/**", "/login/oauth2/code/google", "/login", "/signin/**", "/more_info","/bookmarks/**","/codi-requests/**","/oauth2/**")
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/", "/login", "/more_info", "/css/**",
                                "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**",
                                "/webjars/**", "/oauth2/**", "/auth/**", "/kakao/callback"
                        ).permitAll()
                        .requestMatchers(
                                "/users/**", "/auth/**", "/ootds/**", "/clothes/**",
                                "/closets/**", "/votes/**", "/login/oauth2/code/google",
                                "/login", "/more_info","/bookmarks/","/codi-requests/**"
                        ).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);// 필터 추가







        return http.build();
    }

    // CORS 정책 추가
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // 모든 도메인 허용 (배포 시 특정 도메인으로 변경)
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true); // 쿠키 포함 요청 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}