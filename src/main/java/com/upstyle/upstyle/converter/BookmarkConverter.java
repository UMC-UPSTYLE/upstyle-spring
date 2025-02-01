package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;
import com.upstyle.upstyle.web.dto.OotdResponseDTO;

public class BookmarkConverter {
    public static BookmarkResponseDTO toBookmarkResponseDTO(ClothBookmark bookmark) {
        return BookmarkResponseDTO.builder()
                .id(bookmark.getId())
                .userId(bookmark.getUser().getId())
                .clothId(bookmark.getCloth().getId())
                .build();
    }

}
