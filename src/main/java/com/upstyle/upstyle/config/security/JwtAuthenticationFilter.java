package com.upstyle.upstyle.config.security;

import com.upstyle.upstyle.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public JwtAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        System.out.println("📌 Extracted Token: " + token); // 🚀 로그 추가

        if (token != null) {
            boolean isValid = tokenService.validateToken(token);
            System.out.println("📌 Is Token Valid?: " + isValid); // 🚀 로그 추가

            if (isValid) {
                String email = tokenService.getEmail(token);
                if (email != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("📌 Authentication set for user: " + email); // 🚀 로그 추가
                } else {
                    System.out.println("❌ Failed to extract email from token"); // 🚀 로그 추가
                }
            } else {
                System.out.println("❌ Token validation failed"); // 🚀 로그 추가
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        System.out.println("Authorization 헤더 값: " + bearerToken); // 로그 추가

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return null; // 토큰이 없거나 잘못된 형식이면 null 반환
        }
        String token = bearerToken.substring(7); // "Bearer " 이후의 값만 추출
        System.out.println("추출된 JWT 토큰: " + token); // 로그 추가
        return token;
    }
}