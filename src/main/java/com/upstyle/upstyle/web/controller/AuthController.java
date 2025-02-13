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
import org.springframework.web.client.HttpClientErrorException;
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
    @Operation(summary = "사용 X")
    public ResponseEntity<String> redirectToKakaoLogin() {
        String kakaoLoginUrl = kakaoAuthService.getKakaoLoginUrl();
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", kakaoLoginUrl).build();
    }

    // 인가 코드 받아서 액세스 토큰 발급
    @GetMapping("/kakao/callback")
    @Operation(summary = "인가코드 요청해서 액세스 토큰 응답받음")
    public ResponseEntity<KakaoTokenResponse> getKakaoToken(@RequestParam("code") String code) {
        KakaoTokenResponse tokenResponse = kakaoAuthService.getAccessToken(code);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/kakao/loginJWT")
    @Operation(summary = "액세스 토큰으로 사용자 정보 받고 저장 후 jwt토큰으로 반환")
    public ResponseEntity<String> loginWithKakao(@RequestParam("access_token") String accessToken) {
        // 카카오 액세스 토큰으로 JWT 토큰 발급
        String jwtToken = kakaoAuthService.loginWithKakao(accessToken);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/logout")
    @Operation(summary = "카카오 로그아웃 API(로그아웃은 Authorization header값이 jwt가 아닌 accessToken입니다.)")
    public ApiResponse<String> kakaoLogout(@RequestHeader("Authorization") String accessToken) {
        String kakaoLogoutUrl = "https://kapi.kakao.com/v1/user/logout";

        // Token이 올바르게 설정되었는지 로그로 확인
        System.out.println("📌 Kakao Access Token: " + accessToken);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        // Bearer 토큰 포맷 설정
        headers.set("Authorization", accessToken.startsWith("Bearer ") ? accessToken : "Bearer " + accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            // API 요청 보내기
            ResponseEntity<String> response = restTemplate.exchange(kakaoLogoutUrl, HttpMethod.POST, request, String.class);

            // 성공 여부에 따른 처리
            if (response.getStatusCode() == HttpStatus.OK) {
                SecurityContextHolder.clearContext(); // Spring Security 컨텍스트 초기화
                return ApiResponse.onSuccess("로그아웃 성공");
            } else {
                throw new RuntimeException("카카오 로그아웃 실패, 응답 코드: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            // 401 오류 발생 시 로그를 통해 원인 파악
            System.out.println("❌ 카카오 로그아웃 실패, 오류 메시지: " + e.getMessage());
            throw new RuntimeException("카카오 로그아웃 실패", e);
        }
    }
}
