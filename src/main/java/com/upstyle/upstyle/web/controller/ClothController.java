package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.service.ClothService.ClothQueryService;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/clothes")
public class ClothController {

    private final ClothQueryService clothQueryService;

    @GetMapping("/")
    @Operation(summary = "옷장 조회 API")
    public ApiResponse<ClothResponseDTO.ClothKindListDTO> getClothKindList(@RequestParam(value = "userId") Long userId) {
        // 서비스 호출
        ClothResponseDTO.ClothKindListDTO clothKindList = clothQueryService.getClothKindList(userId);

        return ApiResponse.onSuccess(clothKindList);
    }

    @GetMapping("/{kindId}")
    @Operation(summary = "옷 종류에 따른 옷 리스트 조회 API")
    public ApiResponse<ClothResponseDTO.ClothPreviewListDTO> getClothPreviewList(@PathVariable("kindId") Long kindId,
                                                                                 @RequestParam(value = "userId") Long userId,
                                                                                 @RequestParam(value = "categoryId", required = false) Long categoryId,
                                                                                 @RequestParam(value = "colorId", required = false) Long colorId,@RequestParam(value = "fitId", required = false) Long fitId, @RequestParam(value = "page", required = false, defaultValue = "0") int page,
    @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        ClothResponseDTO.ClothPreviewListDTO previewList = clothQueryService.getClothPreviewList(userId, kindId, categoryId, colorId, fitId, page, size);
        return ApiResponse.onSuccess(previewList);
    }
}
