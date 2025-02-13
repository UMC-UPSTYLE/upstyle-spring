package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.converter.UserConverter;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.service.AuthService.KakaoAuthService;
import com.upstyle.upstyle.service.UserService.UserCommandService;
import com.upstyle.upstyle.web.dto.AuthResponseDTO;
import com.upstyle.upstyle.web.dto.KakaoTokenResponse;
import com.upstyle.upstyle.web.dto.KakaoUserInfoResponseDto;
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
    private final KakaoAuthService kakaoAuthService;

    @GetMapping("/kakao/login")
    public ResponseEntity<String> redirectToKakaoLogin() {
        String kakaoLoginUrl = kakaoAuthService.getKakaoLoginUrl();
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", kakaoLoginUrl).build();
    }

    // 인가 코드 받아서 액세스 토큰 발급
    @GetMapping("/kakao/callback")
    public ResponseEntity<KakaoTokenResponse> getKakaoToken(@RequestParam("code") String code) {
        KakaoTokenResponse tokenResponse = kakaoAuthService.getAccessToken(code);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/kakao/loginJWT")
    public ResponseEntity<String> loginWithKakao(@RequestParam("access_token") String accessToken) {
        // 카카오 액세스 토큰으로 JWT 토큰 발급
        String jwtToken = kakaoAuthService.loginWithKakao(accessToken);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/logout")
    @Operation(summary = "카카오 로그아웃 API")
    public ApiResponse<String> kakaoLogout(@RequestHeader("Authorization") String accessToken) {
        String kakaoLogoutUrl = "https://kapi.kakao.com/v1/user/logout";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken.startsWith("Bearer ") ? accessToken : "Bearer " + accessToken);
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
