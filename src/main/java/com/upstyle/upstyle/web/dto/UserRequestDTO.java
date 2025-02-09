package com.upstyle.upstyle.web.dto;

import com.upstyle.upstyle.domain.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



public class UserRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdditionalInfoRequestDTO {  // ✅ 별도 클래스로 분리
        @NotBlank
        private String nickname;
        @NotNull
        private Gender gender;
        private Float height;
        private Float weight;
    }
}
