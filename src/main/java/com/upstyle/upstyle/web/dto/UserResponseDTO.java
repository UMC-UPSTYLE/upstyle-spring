package com.upstyle.upstyle.web.dto;

import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;


public class UserResponseDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserInfoDTO {
        private Long id;
        private String nickname;
        private String email;
        private Gender gender;
        private Float height;
        private Float weight;
    }
}
