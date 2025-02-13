package com.upstyle.upstyle.service.UserService;

import com.upstyle.upstyle.converter.UserConverter;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.enums.Gender;
import com.upstyle.upstyle.domain.enums.Role;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String NICKNAME_PATTERN = "^[가-힣a-zA-Z0-9]+$";

    public User updateUserInfo(String email, UserRequestDTO.AdditionalInfoRequestDTO additionalInfoRequestDTO) {
        // 사용자의 정보를 찾아서 업데이트하는 로직
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.setNickname(additionalInfoRequestDTO.getNickname());
        user.setGender(Gender.valueOf(String.valueOf(additionalInfoRequestDTO.getGender()))); // gender 값이 "MALE", "FEMALE"이기 때문에 대문자로 변환
        user.setWeight(additionalInfoRequestDTO.getWeight());
        user.setHeight(additionalInfoRequestDTO.getHeight());

        return userRepository.save(user); // 업데이트된 사용자 정보 저장
    }


    @Transactional
    public User updateNickname(String email, String newNickname) {
        // 닉네임 유효성 검사
        validateNickname(newNickname);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 중복 닉네임 검사
        if (userRepository.existsByNickname(newNickname)) {
            throw new RuntimeException("이미 사용 중인 닉네임입니다.");
        }

        user.updateNickname(newNickname);
        return userRepository.save(user);
    }

    private void validateNickname(String nickname) {
        if (nickname.length() > 8) {
            throw new IllegalArgumentException("닉네임은 최대 8자까지 가능합니다.");
        }
        if (!nickname.matches(NICKNAME_PATTERN)) {
            throw new IllegalArgumentException("닉네임은 한글, 영문자, 숫자만 사용 가능합니다.");
        }
    }
    public User saveOrUpdateUser(String email, String nickname) {
        // 이미 존재하는 사용자 체크
        User user = userRepository.findByEmail(email)
                .orElse(User.builder()
                        .email(email)
                        .nickname(nickname)
                        .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
                        .role(Role.USER) // 기본 권한 사용자
                        .build());

        return userRepository.save(user);
    }
}
