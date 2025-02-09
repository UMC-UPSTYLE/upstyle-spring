package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.web.dto.UserRequestDTO;
import com.upstyle.upstyle.web.dto.UserResponseDTO;

public class UserConverter {

    // User 엔티티를 UserInfoDTO로 변환
    public static UserResponseDTO.UserInfoDTO toUserInfoDTO(User user) {
        return UserResponseDTO.UserInfoDTO.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .gender(user.getGender())
                .height(user.getHeight())
                .weight(user.getWeight())
                .build();
    }

    // AdditionalInfoRequestDTO를 사용하여 User 엔티티를 업데이트
    public static void updateUserWithAdditionalInfo(User user, UserRequestDTO.AdditionalInfoRequestDTO additionalInfoRequestDTO) {
        user.setNickname(additionalInfoRequestDTO.getNickname());
        user.setGender(additionalInfoRequestDTO.getGender());
        user.setHeight(additionalInfoRequestDTO.getHeight());
        user.setWeight(additionalInfoRequestDTO.getWeight());
    }
}
