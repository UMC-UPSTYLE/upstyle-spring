package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.converter.UserConverter;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.service.UserService.UserCommandService;
import com.upstyle.upstyle.web.dto.AuthResponseDTO;
import com.upstyle.upstyle.web.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/auth")
@Slf4j
public class AuthController {

    private final UserCommandService userCommandService;

    @GetMapping("/login/kakao")
    @Operation(summary = "카카오 로그인 후 jwt 반환")
    public ApiResponse<AuthResponseDTO.JwtDTO> kakaoLogin(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String jwt = (String) oAuth2User.getAttributes().get("jwt");
        return ApiResponse.onSuccess(AuthResponseDTO.JwtDTO.builder().jwt(jwt).build());
    }
}
