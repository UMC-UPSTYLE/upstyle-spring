package com.upstyle.upstyle.service;

import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import com.upstyle.upstyle.web.dto.BookmarkRequestDTO;
import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;

public interface BookmarkCommandService {
    BookmarkResponseDTO addBookmark(BookmarkRequestDTO bookmarkRequestDTO);
}
