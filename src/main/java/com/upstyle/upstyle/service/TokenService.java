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

    public String generateToken(String nickname, String email, String role) {
        System.out.println("📌 [generateToken] nickname: " + nickname);
        System.out.println("📌 [generateToken] email: " + email);
        System.out.println("📌 [generateToken] role: " + role);

        Claims claims = Jwts.claims();
        claims.put("email", email);  // ✅ 이메일 필드 추가
        claims.put("nickname", nickname);  // ✅ 닉네임 필드 추가
        claims.put("role", role);  // ✅ 역할 필드 추가

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email) // ✅ `sub`(subject)에 이메일 저장 (이메일 기반 인증을 고려)
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
            e.printStackTrace(); // ❌ 오류 발생 원인을 확인하기 위해 로그 추가
            return false;
        }
    }

    public String getEmail(String token) {
        System.out.println("📌 [getEmail] 입력된 JWT: " + token); // 🚀 로그 추가

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String email = (String) claims.get("email");
        System.out.println("📌 [getEmail] 추출된 이메일: " + email); // 🚀 로그 추가
        return email;
    }


}
