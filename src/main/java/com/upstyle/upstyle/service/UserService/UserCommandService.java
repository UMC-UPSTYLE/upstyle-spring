package com.upstyle.upstyle.service.UserService;

import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.web.dto.UserRequestDTO;

public interface UserCommandService {
    User updateUserInfo(Long userId, UserRequestDTO.AdditionalInfoRequestDTO additionalInfoRequestDTO);
    User updateNickname(Long userId, String newNickname);
    User saveOrUpdateUser(String email, String nickName);
}
