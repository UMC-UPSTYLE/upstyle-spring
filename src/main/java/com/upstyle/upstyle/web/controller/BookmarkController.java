package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.service.BookmarkService.BookmarkCommandService;
import com.upstyle.upstyle.service.BookmarkService.BookmarkQueryService;
import com.upstyle.upstyle.web.dto.BookmarkRequestDTO;
import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
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

    @PostMapping(value = "/")
    @Operation(summary = "북마크 저장/취소 API")
    public ApiResponse<BookmarkResponseDTO.AddBookmarkResultDTO> addBookmark(
            @RequestBody @Valid BookmarkRequestDTO.AddBookmarkDTO request)
    {
        BookmarkResponseDTO.AddBookmarkResultDTO clothBookmark = bookmarkCommandService.addBookmark(request);
        return ApiResponse.onSuccess(clothBookmark);
    }

    @GetMapping("/")
    @Operation(summary = "북마크된 옷 리스트 조회 API")
    public ApiResponse<BookmarkResponseDTO.BookmarkListDTO> getBookmarks(
            @RequestParam Long userId,
            @RequestParam(value = "kindId", required = false) Long kindId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        BookmarkResponseDTO.BookmarkListDTO bookmarkedClothes = bookmarkQueryService.getBookmarkedClothes(userId, kindId, page, size);
        return ApiResponse.onSuccess(bookmarkedClothes);
    }
}
