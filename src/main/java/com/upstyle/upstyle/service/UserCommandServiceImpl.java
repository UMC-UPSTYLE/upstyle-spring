package com.upstyle.upstyle.service;

import com.upstyle.upstyle.converter.UserConverter;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.enums.Gender;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User addAdditionalInfo(String email, UserRequestDTO.AdditionalInfoRequestDTO additionalInfoRequestDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 사용자 정보 업데이트
        user.setNickname(additionalInfoRequestDTO.getNickname());
        user.setGender(additionalInfoRequestDTO.getGender());
        user.setHeight(additionalInfoRequestDTO.getHeight());
        user.setWeight(additionalInfoRequestDTO.getWeight());

        return userRepository.save(user); // 업데이트된 사용자 정보 반환
    }
}
