package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.converter.UserConverter;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.service.UserService.UserCommandService;
import com.upstyle.upstyle.web.dto.AuthResponseDTO;
import com.upstyle.upstyle.web.dto.UserRequestDTO;
import com.upstyle.upstyle.web.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/auth")
public class AuthController {

    private final UserCommandService userCommandService;

    @GetMapping("/login/kakao")
    @Operation(summary = "카카오 로그인 후 jwt 반환")
    public ApiResponse<AuthResponseDTO.JwtDTO> kakaoLogin(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String jwt = (String) oAuth2User.getAttributes().get("jwt");
        return ApiResponse.onSuccess(AuthResponseDTO.JwtDTO.builder().jwt(jwt).build());
    }

    @PostMapping("/additional-info")
    @Operation(summary = "추가 정보 입력 API")
    public ApiResponse<UserResponseDTO.UserInfoDTO> addAdditionalInfo(
            @RequestBody @Valid UserRequestDTO.AdditionalInfoRequestDTO additionalInfoRequestDTO,
            Authentication authentication) {
        // 현재 로그인한 사용자 정보 가져오기
        String email = (String) authentication.getPrincipal();

        // 추가 정보 저장 및 업데이트된 사용자 정보 가져오기
        User updatedUser = userCommandService.addAdditionalInfo(email, additionalInfoRequestDTO);

        // 사용자 정보를 DTO로 변환하여 반환
        return ApiResponse.onSuccess(UserConverter.toUserInfoDTO(updatedUser));
    }
}
