package com.upstyle.upstyle.service.BookmarkService;

import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import com.upstyle.upstyle.web.dto.BookmarkRequestDTO;
import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;

public interface BookmarkCommandService {
    BookmarkResponseDTO.AddBookmarkResultDTO addBookmark(Long userId, BookmarkRequestDTO.AddBookmarkDTO bookmarkRequestDTO);
}
