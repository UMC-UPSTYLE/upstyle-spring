package com.upstyle.upstyle.config.security;

import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.enums.Gender;
import com.upstyle.upstyle.domain.enums.Role;
import com.upstyle.upstyle.repository.UserRepository;
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
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2 기본 사용자 정보 가져오기
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 사용자 정보 속성 가져오기
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        // 사용자 저장 또는 업데이트
        User user = saveOrUpdateUser(email, name);

        // JWT 생성
        String jwt = jwtTokenProvider.createToken(user.getId(), user.getRole().name());

        // 속성 정보에 추가 데이터(JWT 등) 삽입 가능
        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
        modifiedAttributes.put("jwt", jwt);

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                modifiedAttributes,
                "email" // 사용자 Principal로 사용할 필드
        );
    }

    private User saveOrUpdateUser(String email, String name) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .email(email)
                                .nickname(name)
                                .role(Role.USER)
                                .build()
                ));
    }
}
