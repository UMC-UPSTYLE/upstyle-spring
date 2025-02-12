package com.upstyle.upstyle.web.dto;

import com.upstyle.upstyle.domain.User;
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

        public static AccountInfoDTO fromEntity(User user) {
            return AccountInfoDTO.builder()
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .gender(user.getGender())
                    .weight(user.getWeight())
                    .height(user.getHeight())
                    .build();
        }

    }


    @Getter
    @AllArgsConstructor
    public static class NicknameDTO {
        private String nickname;
    }
}
