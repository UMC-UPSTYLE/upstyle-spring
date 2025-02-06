package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.converter.OotdConverter;
import com.upstyle.upstyle.domain.Ootd;
import com.upstyle.upstyle.service.OotdService.OotdCommandService;
import com.upstyle.upstyle.service.OotdService.OotdQueryService;
import com.upstyle.upstyle.web.dto.OotdRequestDTO;
import com.upstyle.upstyle.web.dto.OotdResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/ootds")
public class OotdController {

    private final OotdCommandService ootdCommandService;
    private final OotdQueryService ootdQueryService;

    @PostMapping(value = "/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "ootd 생성 API")
    public ApiResponse<OotdResponseDTO.addOotdResultDTO> addOotd(
            @Parameter(description = "OOTD 데이터", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("request") @Valid OotdRequestDTO.addOotdDTO request,

            @Parameter(description = "이미지 파일들", content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                    array = @ArraySchema(schema = @Schema(type = "string", format = "binary"))))
            @RequestPart("ootdImages") MultipartFile[] ootdImages) {

        Ootd ootd = ootdCommandService.addOotd(request, ootdImages);
        return ApiResponse.onSuccess(OotdConverter.toAddOotdResultDTO(ootd));
    }

    @GetMapping("/{ootdId}")
    @Operation(summary = "ootd 조회 API")
    public ApiResponse<OotdResponseDTO.OotdDTO> getOotd(@PathVariable Long ootdId){
        OotdResponseDTO.OotdDTO ootdDTO = ootdQueryService.getOotdById(ootdId);
        return ApiResponse.onSuccess(ootdDTO);
    }

    @GetMapping("/calendar")
    public ApiResponse<OotdResponseDTO.CalendarResponseDTO> getDateOotd(@RequestParam(value = "userId") Long userId,
                                                                        @RequestParam(value = "year") int year,
                                                                        @RequestParam(value = "month") int month){
        OotdResponseDTO.CalendarResponseDTO CalendarResponse = ootdQueryService.getCalendarResponseDTO(userId, year, month);
        return ApiResponse.onSuccess(CalendarResponse);
    }

}
