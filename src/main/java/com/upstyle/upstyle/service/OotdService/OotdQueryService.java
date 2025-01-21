package com.upstyle.upstyle.service.OotdService;

import com.upstyle.upstyle.web.dto.OotdResponseDTO;

public interface OotdQueryService {
    OotdResponseDTO.CalendarResponseDTO getCalendarResponseDTO(Long userId, int year, int month);
}
