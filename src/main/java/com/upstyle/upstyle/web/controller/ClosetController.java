package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.service.ClosetService.ClosetQueryService;
import com.upstyle.upstyle.service.TokenService;
import com.upstyle.upstyle.web.dto.ClosetResponseDTO;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/closets")
public class ClosetController {
    private final ClosetQueryService closetQueryService;
    private final TokenService tokenService;

    @GetMapping
    @Operation(summary = "내 옷장 조회 API")
    public ApiResponse<ClosetResponseDTO.ClothKindListDTO> getMyCloset(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("JWT 토큰이 필요합니다.");
        }

        token = token.substring(7); // "Bearer " 제거
        Long userId = tokenService.getId(token); // JWT에서 userId 추출

        ClosetResponseDTO.ClothKindListDTO clothKindList = closetQueryService.getClothKindList(userId);
        return ApiResponse.onSuccess(clothKindList);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "특정 사용자 옷장 조회 API")
    public ApiResponse<ClosetResponseDTO.ClothKindListDTO> getUserCloset(@PathVariable Long userId) {
        ClosetResponseDTO.ClothKindListDTO clothKindList = closetQueryService.getClothKindList(userId);
        return ApiResponse.onSuccess(clothKindList);
    }

    @GetMapping("/categories")
    @Operation(summary = "내 옷장 내 카테고리별 조회 API")
    public ApiResponse<ClothResponseDTO.ClothPreviewListDTO> getClothPreviewList(
            HttpServletRequest request,
            @RequestParam(value = "kindId", required = false) Long kindId,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "colorId", required = false) List<Long> colorId,
            @RequestParam(value = "fitId", required = false) Long fitId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("JWT 토큰이 필요합니다.");
        }

        token = token.substring(7); // "Bearer " 제거
        Long userId = tokenService.getId(token); // JWT에서 userId 추출

        ClothResponseDTO.ClothPreviewListDTO previewList = closetQueryService.getClothPreviewList(userId, kindId, categoryId, colorId, fitId, page, size);
        return ApiResponse.onSuccess(previewList);
    }

    @GetMapping("/{userId}/categories")
    @Operation(summary = "특정 사용자 옷장 내 카테고리별 조회 API")
    public ApiResponse<ClothResponseDTO.ClothPreviewListDTO> getUserClothPreviewList(
            @PathVariable Long userId,
            @RequestParam(value = "kindId", required = false) Long kindId,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "colorId", required = false) List<Long> colorId,
            @RequestParam(value = "fitId", required = false) Long fitId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        ClothResponseDTO.ClothPreviewListDTO previewList = closetQueryService.getClothPreviewList(userId, kindId, categoryId, colorId, fitId, page, size);
        return ApiResponse.onSuccess(previewList);
    }

}
