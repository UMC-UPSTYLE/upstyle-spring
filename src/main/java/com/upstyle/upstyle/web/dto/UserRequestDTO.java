package com.upstyle.upstyle.web.dto;

import com.upstyle.upstyle.domain.enums.Gender;
import com.upstyle.upstyle.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class UserRequestDTO {

    @Getter
    @Setter
    public static class JoinDto{
        public JoinDto() {}
        @NotBlank
        String nickname;
        @NotBlank
        @Email
        String email;
        @NotBlank
        String password;
//        @NotNull
//        Float height;
//        @NotNull
//        Float weight;
        @NotNull
        Integer gender;
        @NotNull
        Role role;
    }
}
