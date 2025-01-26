package com.upstyle.upstyle.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {
    private Key secretKey;

    private final String SECRET_KEY = "PNx/Ldm5F2zgsEJ124+Tq82OYVV/KjdrFmCVOdBfeic="; // Base64로 인코딩된 키
    private final Long EXPIRE_LENGTH = 3600000L; // 1시간

    @PostConstruct
    protected void init() {
        // Base64 키 디코딩 및 SecretKey 초기화
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public String generateToken(String name, String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("name", name);
        claims.put("role", role);

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_LENGTH))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey)
                    .build().parseClaimsJws(token);

            return claims.getBody().getExpiration()
                    .after(new Date(System.currentTimeMillis()));

        } catch (Exception e) {
            return false;
        }
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }
}
