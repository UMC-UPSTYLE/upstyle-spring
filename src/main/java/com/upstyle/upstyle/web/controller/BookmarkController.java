package com.upstyle.upstyle.web.controller;

import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.converter.OotdConverter;
import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import com.upstyle.upstyle.service.BookmarkCommandService;
import com.upstyle.upstyle.web.dto.BookmarkRequestDTO;
import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
