package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.service.BookmarkService.BookmarkCommandService;
import com.upstyle.upstyle.web.dto.BookmarkRequestDTO;
import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/bookmarks")
public class BookmarkController {
    private final BookmarkCommandService bookmarkCommandService;

    @PostMapping(value = "/")
    public ApiResponse<BookmarkResponseDTO> addBookmark(
            @RequestBody @Valid BookmarkRequestDTO request)
    {
        BookmarkResponseDTO clothBookmark = bookmarkCommandService.addBookmark(request);
        return ApiResponse.onSuccess(clothBookmark);
    }
}
