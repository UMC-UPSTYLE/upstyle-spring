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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/auth")
@Slf4j
public class AuthController {

    private final UserCommandService userCommandService;

    @GetMapping("/login/kakao")
    @Operation(summary = "카카오 로그인 후 jwt 반환")
    public ApiResponse<AuthResponseDTO.KakaoJwtDTO> kakaoLogin( OAuth2AuthenticationToken authenticationToken, @AuthenticationPrincipal OAuth2User oAuth2User) {

        // JWT 토큰 가져오기 (기존 로직 유지)
        String jwt = (String) oAuth2User.getAttributes().get("jwt");

        // OAuth2 인증 객체에서 카카오 액세스 토큰 가져오기
//
//        if (kakaoAccessToken == null) {
//            throw new RuntimeException("카카오 액세스 토큰을 가져올 수 없습니다.");
//        }

        return ApiResponse.onSuccess(AuthResponseDTO.KakaoJwtDTO.builder()
                .jwt(jwt)
                //.kakaoAccessToken(kakaoAccessToken)
                .build());
    }

    @PostMapping("/logout")
    @Operation(summary = "카카오 로그아웃 API")
    public ApiResponse<String> kakaoLogout(@RequestHeader("Authorization") String accessToken) {
        String kakaoLogoutUrl = "https://kapi.kakao.com/v1/user/logout";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(kakaoLogoutUrl, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            SecurityContextHolder.clearContext(); // Spring Security 컨텍스트 초기화
            return ApiResponse.onSuccess("로그아웃 성공");
        } else {
            throw new RuntimeException("카카오 로그아웃 실패");
        }
    }
}
