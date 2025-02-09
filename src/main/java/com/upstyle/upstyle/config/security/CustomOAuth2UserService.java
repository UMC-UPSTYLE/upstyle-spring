package com.upstyle.upstyle.config.security;

import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.enums.Gender;
import com.upstyle.upstyle.domain.enums.Role;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 카카오 응답을 로그로 출력
        System.out.println("OAuth2 Response: " + oAuth2User.getAttributes());

        Map<String, Object> attributes = oAuth2User.getAttributes();

        //카카오 응답에서 사용자 정보 추출
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        //카카오에서 제공하는 이메일 가져오기
        String email = (String) kakaoAccount.get("email");
        if (email == null) {
            throw new IllegalArgumentException("카카오 계정에 이메일 정보가 없습니다.");
        }

        String nickname = (String) properties.get("nickname");

        // 사용자 정보 저장 또는 업데이트
        User user = saveOrUpdateUser(email, nickname);

        // 카카오 액세스 토큰 가져오기
        String kakaoAccessToken = userRequest.getAccessToken().getTokenValue();

        String jwt = tokenService.generateToken(user.getNickname(), user.getEmail(), user.getRole().name());


        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
        modifiedAttributes.put("email", email);
        modifiedAttributes.put("jwt", jwt);
        modifiedAttributes.put("kakao_access_token", kakaoAccessToken);  // 카카오 액세스 토큰 추가


        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                modifiedAttributes,
                "email"  // email Principal로 설정
        );
    }

    private User saveOrUpdateUser(String email, String nickname) {
        User member = userRepository.findByEmail(email)
                .orElse(User.builder()
                        .email(email)
                        .nickname(nickname)
                        .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
                        .gender(Gender.NONE)  // 기본값 설정
                        .role(Role.USER)
                        .build());

        return userRepository.save(member);
    }
    //테스트
}