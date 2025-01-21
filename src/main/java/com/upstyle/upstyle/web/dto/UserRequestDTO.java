package com.upstyle.upstyle.web.dto;

import com.upstyle.upstyle.domain.enums.Gender;
import com.upstyle.upstyle.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class AdditionalInfoRequestDTO {
        @NotBlank
        private String nickname;  // 닉네임
        @NotNull
        private Gender gender;    // 성별: MALE, FEMALE, NONE
        private Float height;     // 키
        private Float weight;     // 몸무게
    }
}
