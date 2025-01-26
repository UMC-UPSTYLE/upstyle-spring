package com.upstyle.upstyle.service.UserService;

import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.web.dto.UserRequestDTO;

public interface UserCommandService {
    User addAdditionalInfo(String email, UserRequestDTO.AdditionalInfoRequestDTO additionalInfoRequestDTO);
}
