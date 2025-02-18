package com.upstyle.upstyle.service.AuthService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.enums.Gender;
import com.upstyle.upstyle.domain.enums.Role;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.service.TokenService;
import com.upstyle.upstyle.web.dto.KakaoTokenResponse;
import com.upstyle.upstyle.web.dto.KakaoUserInfoResponseDto;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoAuthService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(KakaoAuthService.class); // Logger 선언


    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    private static final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String KAKAO_AUTH_URL = "https://kauth.kakao.com/oauth/authorize";
    private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    public String getKakaoLoginUrl() {
        return KAKAO_AUTH_URL +
                "?response_type=code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&scope=profile_nickname,account_email";
    }

    public KakaoTokenResponse getAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret); // 선택 사항

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<KakaoTokenResponse> response = restTemplate.postForEntity(KAKAO_TOKEN_URL, request, KakaoTokenResponse.class);

        return response.getBody();
    }

    /**
     * 카카오 액세스 토큰으로 사용자 정보 가져오기
     */

    public KakaoUserInfoResponseDto getUserInfo(String kakaoAccessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(kakaoAccessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<KakaoUserInfoResponseDto> response = restTemplate.exchange(
                KAKAO_USER_INFO_URL,
                HttpMethod.GET,
                entity,
                KakaoUserInfoResponseDto.class
        );

        log.info("[ Kakao Service ] Auth ID ---> {} ", response.getBody().getId());
        log.info("[ Kakao Service ] NickName ---> {} ", response.getBody().getKakaoAccount().getProfile().getNickName());
        log.info("[ Kakao Service ] Email ---> {} ", response.getBody().getKakaoAccount().getEmail());

        return response.getBody();
    }
    /**
     * 카카오 액세스 토큰으로 사용자 정보 가져와서 JWT 토큰 반환
     */
    public String loginWithKakao(String kakaoAccessToken) {
        // 카카오 액세스 토큰으로 사용자 정보 요청
        KakaoUserInfoResponseDto userInfo = getUserInfo(kakaoAccessToken);
        System.out.println("Kakao nickname Info: " + userInfo.getKakaoAccount().getProfile().getNickName());
        System.out.println("Kakao email Info: " + userInfo.getKakaoAccount().getEmail());

        String email = userInfo.getKakaoAccount().getEmail();
        String nickname = userInfo.getKakaoAccount().getProfile().getNickName();

        // 사용자 정보로 회원가입 또는 로그인 처리
        User user = saveOrUpdateUser(email, nickname);

        // JWT 토큰 생성 및 반환 (id 추가)
        return tokenService.generateToken(user.getId(), user.getNickname(), user.getEmail(), String.valueOf(user.getRole()));
    }

    private User saveOrUpdateUser(String email, String nickname) {
        // 이미 존재하는 사용자 체크
        User user = userRepository.findByEmail(email)
                .orElse(User.builder()
                        .email(email)
                        .nickname(nickname)
                        .role(Role.USER) // 기본 권한 사용자
                        .build());

        return userRepository.save(user);
    }
}
