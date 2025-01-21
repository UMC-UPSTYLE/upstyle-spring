package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.service.OotdService.OotdQueryService;
import com.upstyle.upstyle.web.dto.OotdResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/calendar")
public class CalendarController {
    private final OotdQueryService ootdQueryService;

    @GetMapping("/")
    public ApiResponse<OotdResponseDTO.CalendarResponseDTO> getDateOotd(@RequestParam(value = "userId") Long userId,
                                                                        @RequestParam(value = "year") int year,
                                                                        @RequestParam(value = "month") int month){
        OotdResponseDTO.CalendarResponseDTO CalendarResponse = ootdQueryService.getCalendarResponseDTO(userId, year, month);
        return ApiResponse.onSuccess(CalendarResponse);
    }
}
