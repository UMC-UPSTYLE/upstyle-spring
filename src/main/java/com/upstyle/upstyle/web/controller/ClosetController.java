package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.service.ClosetService.ClosetQueryService;
import com.upstyle.upstyle.web.dto.ClosetResponseDTO;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/closet")
public class ClosetController {
    private final ClosetQueryService closetQueryService;

    @GetMapping("/")
    @Operation(summary = "사용자 옷장 조회 API")
    public ApiResponse<ClosetResponseDTO.ClothKindListDTO> getClothKindList(@RequestParam(value = "userId") Long userId) {
        ClosetResponseDTO.ClothKindListDTO clothKindList = closetQueryService.getClothKindList(userId);
        return ApiResponse.onSuccess(clothKindList);
    }

    @GetMapping("/categories")
    @Operation(summary = "사용자 옷장 내 카테고리별 조회 API")
    public ApiResponse<ClothResponseDTO.ClothPreviewListDTO> getClothPreviewList(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "kindId", required = false) Long kindId,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "colorId", required = false) Long colorId,
            @RequestParam(value = "fitId", required = false) Long fitId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        ClothResponseDTO.ClothPreviewListDTO previewList = closetQueryService.getClothPreviewList(userId, kindId, categoryId, colorId, fitId, page, size);
        return ApiResponse.onSuccess(previewList);
    }

}
