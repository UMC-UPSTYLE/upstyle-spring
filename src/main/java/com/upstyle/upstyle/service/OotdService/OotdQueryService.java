package com.upstyle.upstyle.service.OotdService;

import com.upstyle.upstyle.web.dto.OotdResponseDTO;

public interface OotdQueryService {
    OotdResponseDTO.CalenderResponseDTO getCalenderResponseDTO(Long userId, int year, int month);
}
