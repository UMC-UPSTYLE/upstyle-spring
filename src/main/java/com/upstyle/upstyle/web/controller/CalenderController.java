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
@RequestMapping(value = "/calender")
public class CalenderController {
    private final OotdQueryService ootdQueryService;

    @GetMapping("/")
    public ApiResponse<OotdResponseDTO.CalenderResponseDTO> getDateOotd(@RequestParam(value = "userId") Long userId,
                                                                        @RequestParam(value = "year") int year,
                                                                        @RequestParam(value = "month") int month){
        OotdResponseDTO.CalenderResponseDTO CalenderResponse = ootdQueryService.getCalenderResponseDTO(userId, year, month);
        return ApiResponse.onSuccess(CalenderResponse);
    }
}
