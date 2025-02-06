package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.service.ClothService.ClothQueryService;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/clothes")
public class ClothController {

    private final ClothQueryService clothQueryService;

    @GetMapping("/categories")
    @Operation(summary = "전체 사용자 옷 중 카테고리별 조회 API")
    public ApiResponse<ClothResponseDTO.ClothPreviewListDTO> getClothPreviewList(@RequestParam(value = "kindId", required = false) Long kindId,
                                                                                 @RequestParam(value = "categoryId", required = false) Long categoryId,
                                                                                 @RequestParam(value = "colorIds", required = false) List<Long> colorId, @RequestParam(value = "fitId", required = false) Long fitId, @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        ClothResponseDTO.ClothPreviewListDTO previewList = clothQueryService.getClothPreviewList(kindId, categoryId, colorId, fitId, page, size);
        return ApiResponse.onSuccess(previewList);
    }
}
