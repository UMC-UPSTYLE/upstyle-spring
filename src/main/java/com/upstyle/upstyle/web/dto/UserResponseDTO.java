package com.upstyle.upstyle.web.dto;

import com.upstyle.upstyle.domain.User;
import lombok.*;

import java.time.LocalDateTime;

public class UserResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfoDTO {
        private Long id;
        private String nickname;
        private String email;
        private String gender;
        private Float height;
        private Float weight;
    }
}
