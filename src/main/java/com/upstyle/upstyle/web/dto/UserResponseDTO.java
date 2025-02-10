package com.upstyle.upstyle.web.dto;

import com.upstyle.upstyle.domain.enums.Gender;
import lombok.*;


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

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AccountInfoDTO {
        private String email;
        private String nickname;
        private Float height;
        private Float weight;
        private Gender gender;
    }


    @Getter
    @AllArgsConstructor
    public static class NicknameDTO {
        private String nickname;
    }
}
