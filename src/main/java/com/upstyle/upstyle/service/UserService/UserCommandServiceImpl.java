package com.upstyle.upstyle.service.UserService;

import com.upstyle.upstyle.converter.UserConverter;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.enums.Gender;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

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
}
