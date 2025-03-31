package com.upstyle.upstyle.web.dto;

import com.upstyle.upstyle.domain.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Getter
    @Setter
    public static class NicknameRequestDTO {

        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        @Size(max = 8, message = "닉네임은 2자 이상 8자 이하만 가능합니다.")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$", message = "닉네임은 한글, 영문자, 숫자만 사용 가능합니다.")
        private String nickname;
    }
}
