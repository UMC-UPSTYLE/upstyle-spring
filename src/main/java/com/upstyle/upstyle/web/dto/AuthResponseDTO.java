package com.upstyle.upstyle.web.dto;

import lombok.*;

public class AuthResponseDTO {


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JwtDTO {
        private String jwt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KakaoJwtDTO {
        private String jwt;               // 기존 JWT
        private String kakaoAccessToken;  // 추가된 카카오 액세스 토큰
    }
}
