package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.service.BookmarkService.BookmarkCommandService;
import com.upstyle.upstyle.service.BookmarkService.BookmarkQueryService;
import com.upstyle.upstyle.service.TokenService;
import com.upstyle.upstyle.web.dto.BookmarkRequestDTO;
import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/bookmarks")
public class BookmarkController {
    private final BookmarkCommandService bookmarkCommandService;
    private final BookmarkQueryService bookmarkQueryService;
    private final TokenService tokenService; // ✅ JWT 파싱을 위한 TokenService 추가

    @PostMapping(value = "/")
    @Operation(summary = "북마크 저장/취소 API")
    public ApiResponse<BookmarkResponseDTO.AddBookmarkResultDTO> addBookmark(
            HttpServletRequest request, // ✅ JWT 토큰을 가져오기 위해 HttpServletRequest 추가
            @RequestBody @Valid BookmarkRequestDTO.AddBookmarkDTO bookmarkRequest)
    {
        String token = request.getHeader("Authorization"); // ✅ Authorization 헤더에서 JWT 추출
        token = token.substring(7); // ✅ "Bearer " 제거
        Long userId = tokenService.getId(token); // ✅ JWT에서 userId 추출

        BookmarkResponseDTO.AddBookmarkResultDTO clothBookmark = bookmarkCommandService.addBookmark(userId, bookmarkRequest);
        return ApiResponse.onSuccess(clothBookmark);
    }

    @GetMapping("/")
    @Operation(summary = "북마크된 옷 리스트 조회 API")
    public ApiResponse<BookmarkResponseDTO.BookmarkListDTO> getBookmarks(
            HttpServletRequest request, // JWT 토큰을 가져오기 위해 HttpServletRequest 추가
            @RequestParam(value = "kindId", required = false) Long kindId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        String token = request.getHeader("Authorization"); // Authorization 헤더에서 JWT 추출
        token = token.substring(7); // ✅ "Bearer " 제거
        Long userId = tokenService.getId(token); // JWT에서 userId 추출

        BookmarkResponseDTO.BookmarkListDTO bookmarkedClothes =
                bookmarkQueryService.getBookmarkedClothes(userId, kindId, page, size); // userId 전달
        return ApiResponse.onSuccess(bookmarkedClothes);
    }
}
