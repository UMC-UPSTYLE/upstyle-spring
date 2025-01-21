package com.upstyle.upstyle.web.dto;

import lombok.*;

public class AuthResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JwtDTO{
        private String jwt;
    }
}
