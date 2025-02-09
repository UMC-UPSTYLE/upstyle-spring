package com.upstyle.upstyle.service.BookmarkService;

import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;

import java.util.List;

public interface BookmarkQueryService {
    BookmarkResponseDTO.BookmarkListDTO getBookmarkedClothes(Long userId, Long kindId, int page, int size);
}
