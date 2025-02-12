package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.converter.UserConverter;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.service.TokenService;
import com.upstyle.upstyle.service.UserService.UserCommandService;
import com.upstyle.upstyle.web.dto.UserRequestDTO;
import com.upstyle.upstyle.web.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @PostMapping("/additional-info")
    @Operation(summary = "사용자 추가 정보 입력 API")
    public ApiResponse<UserResponseDTO.UserInfoDTO> addAdditionalInfo(
            @RequestBody @Valid UserRequestDTO.AdditionalInfoRequestDTO additionalInfoRequestDTO,
            Authentication authentication) {

        // 이메일을 attributes에서 가져오기
        String email = tokenService.getEmail(authentication.getName());

        // 추가 정보 저장 후 업데이트된 사용자 정보 가져오기
        User updatedUser = userCommandService.updateUserInfo(email, additionalInfoRequestDTO);

        // DTO로 변환하여 응답 반환
        return ApiResponse.onSuccess(UserConverter.toUserInfoDTO(updatedUser));
    }

    @GetMapping("/account")
    @Operation(summary = "계정 정보 조회 API")
    public ApiResponse<UserResponseDTO.AccountInfoDTO> getAccountInfo(Authentication authentication) {
        // 현재 로그인한 사용자의 OAuth2User 정보 가져오기
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // 이메일을 attributes에서 가져오기
        String email = (String) oAuth2User.getAttributes().get("email");

        // 사용자 정보 조회
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // DTO로 변환하여 응답 반환
        return ApiResponse.onSuccess(UserConverter.toAccountInfoDTO(user));
    }


    @PatchMapping("/nickname")
    @Operation(summary = "닉네임 변경 API")
    public ApiResponse<UserResponseDTO.NicknameDTO> updateNickname(
            @RequestBody @Valid UserRequestDTO.NicknameRequestDTO requestDTO,
            Authentication authentication) {

        // 현재 로그인한 사용자의 OAuth2User 정보 가져오기
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // 이메일을 attributes에서 가져오기
        String email = (String) oAuth2User.getAttributes().get("email");

        // 닉네임 변경
        User updatedUser = userCommandService.updateNickname(email, requestDTO.getNickname());

        // 변경된 닉네임 응답
        return ApiResponse.onSuccess(new UserResponseDTO.NicknameDTO(updatedUser.getNickname()));
    }

}
