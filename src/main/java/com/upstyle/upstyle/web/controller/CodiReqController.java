package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.converter.CodiReqConverter;
import com.upstyle.upstyle.domain.CodiRequest;
import com.upstyle.upstyle.domain.CodiResponse;
import com.upstyle.upstyle.service.OotdrequestService.CodiReqCommandService;
import com.upstyle.upstyle.service.OotdrequestService.CodiReqQueryService;
import com.upstyle.upstyle.service.TokenService;
import com.upstyle.upstyle.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codi-requests")
@RequiredArgsConstructor
@Validated
public class CodiReqController {

    private final CodiReqCommandService codiReqCommandService;
    private final CodiReqQueryService codiReqQueryService;
    private final TokenService tokenService;

    @PostMapping(value = "/", consumes = "application/json")
    @Operation(summary = "코디요청 글 작성 API")
    public ApiResponse<CodiReqResponseDTO.CodiReqPreviewDTO> createCodirequest(
            HttpServletRequest request,
            @RequestBody CodiReqRequestDTO.AddCodiReqDTO codiReqRequestDTO)
    {
        String token = request.getHeader("Authorization");

        token = token.substring(7); // "Bearer " 제거
        Long userId = tokenService.getId(token); // JWT에서 userId 추출

        CodiRequest codiRequest = codiReqCommandService.addCodiReq(userId, codiReqRequestDTO); // userId 전달
        return ApiResponse.onSuccess(CodiReqConverter.toCodiReqPreviewDTO(codiRequest));
    }

    @GetMapping(value = "/")
    @Operation(summary = "코디요청 글 미리보기 리스트 조회 API")
    public ApiResponse<CodiReqResponseDTO.CodiReqPreviewListDTO> getCodiReqPreviewList(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        CodiReqResponseDTO.CodiReqPreviewListDTO previewList = codiReqQueryService.getCodiReqPreviewList(page, size);
        return ApiResponse.onSuccess(previewList);
    }

    @GetMapping(value = "/{requestId}")
    @Operation(summary = "코디요청 글 상세 보기 API")
    public ApiResponse<CodiReqResponseDTO.CodiReqDetailviewDTO> getCodiReqDetailView(@PathVariable(value = "requestId") Long requestId) {
        CodiReqResponseDTO.CodiReqDetailviewDTO CodiReqDTO = codiReqQueryService.getCodiReqDetailview(requestId);
        return ApiResponse.onSuccess(CodiReqDTO);
    }

    @PostMapping(value = "/{requestId}/response", consumes = "application/json")
    @Operation(summary = "코디요청 응답 작성 API")
    public ApiResponse<CodiResResponseDTO.CodiResViewDTO> createCodiresponse(
            HttpServletRequest request,
            @PathVariable(value = "requestId") Long requestId,
            @RequestBody CodiResRequestDTO.addCodiResDTO codiResRequestDTO)
    {
        String token = request.getHeader("Authorization");

        token = token.substring(7); // "Bearer " 제거
        Long userId = tokenService.getId(token); // JWT에서 userId 추출

        CodiResponse codiResponse = codiReqCommandService.addCodiRes(userId, codiResRequestDTO, requestId); // userId 전달
        return ApiResponse.onSuccess(CodiReqConverter.toCodiResViewDTO(codiResponse));
    }

    @GetMapping(value = "/response/{responseId}")
    @Operation(summary = "코디요청 글의 특정 응답 상세 보기 API")
    public ApiResponse<CodiResResponseDTO.CodiResViewDTO> getCodiresResView(@PathVariable(value = "responseId") Long responseId) {
        CodiResResponseDTO.CodiResViewDTO CodiResDTO = codiReqQueryService.getCodiResponse(responseId);
        return ApiResponse.onSuccess(CodiResDTO);
    }
}