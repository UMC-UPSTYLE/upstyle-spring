package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.config.security.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/additional-info")
    @Operation(summary = "사용자 추가 정보 입력 API")
    public ApiResponse<UserResponseDTO.UserInfoDTO> addAdditionalInfo(
            @RequestBody @Valid UserRequestDTO.AdditionalInfoRequestDTO additionalInfoRequestDTO,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Authorization 헤더에서 "Bearer" 제거하고 JWT 토큰만 추출
        String token = authorizationHeader.replace("Bearer ", "");

        // 이메일 추출
        String email = jwtTokenProvider.getEmail(token); // 토큰에서 이메일 추출

        // 추가 정보 저장 후 업데이트된 사용자 정보 가져오기
        User updatedUser = userCommandService.updateUserInfo(email, additionalInfoRequestDTO);

        // DTO로 변환하여 응답 반환
        return ApiResponse.onSuccess(UserConverter.toUserInfoDTO(updatedUser));
    }

    @GetMapping("/")
    @Operation(summary = "유저 정보 조회 API")
    public ApiResponse<UserResponseDTO.AccountInfoDTO> getAccountInfo(
            @RequestHeader("Authorization") String authorizationHeader) {

        // Authorization 헤더에서 "Bearer" 제거하고 JWT 토큰만 추출
        String token = authorizationHeader.replace("Bearer ", "");

        // 이메일 추출
        String email = jwtTokenProvider.getEmail(token); // 토큰에서 이메일 추출

        // 사용자 정보 조회
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // DTO로 변환하여 응답 반환
        return ApiResponse.onSuccess(UserConverter.toAccountInfoDTO(user));
    }


    @PatchMapping("/nickname")
    @Operation(summary = "닉네임 변경 API")
    public ApiResponse<UserResponseDTO.NicknameDTO> updateNickname(
            @RequestBody @Valid UserRequestDTO.NicknameRequestDTO requestDTO,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Authorization 헤더에서 "Bearer" 제거하고 JWT 토큰만 추출
        String token = authorizationHeader.replace("Bearer ", "");

        // 이메일 추출
        String email = jwtTokenProvider.getEmail(token); // 토큰에서 이메일 추출

        // 닉네임 변경
        User updatedUser = userCommandService.updateNickname(email, requestDTO.getNickname());

        // 변경된 닉네임 응답
        return ApiResponse.onSuccess(new UserResponseDTO.NicknameDTO(updatedUser.getNickname()));
    }

}
