package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.converter.OotdConverter;
import com.upstyle.upstyle.domain.Ootd;
import com.upstyle.upstyle.service.OotdCommandService;
import com.upstyle.upstyle.service.OotdCommandServiceImpl;
import com.upstyle.upstyle.web.dto.OotdRequestDTO;
import com.upstyle.upstyle.web.dto.OotdResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/ootds")
public class OotdController {
    private final OotdCommandService ootdCommandService;

    @PostMapping("/")
    public ApiResponse<OotdResponseDTO.addOotdResultDTO> addOotd(@RequestBody @Valid OotdRequestDTO.addOotdDTO request) {
        // Service에서 OOTD 생성 처리
        Ootd ootd = ootdCommandService.addOotd(request);
        return ApiResponse.onSuccess(OotdConverter.toAddOotdResultDTO(ootd));
    }

}