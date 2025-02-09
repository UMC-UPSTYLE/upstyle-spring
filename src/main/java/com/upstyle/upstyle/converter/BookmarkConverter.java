package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;
import com.upstyle.upstyle.web.dto.OotdResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class BookmarkConverter {
    public static BookmarkResponseDTO.AddBookmarkResultDTO toBookmarkResponseDTO(ClothBookmark bookmark) {
        return BookmarkResponseDTO.AddBookmarkResultDTO.builder()
                .id(bookmark.getId())
                .userId(bookmark.getUser().getId())
                .clothId(bookmark.getCloth().getId())
                .build();
    }

    public static BookmarkResponseDTO.BookmarkListDTO toBookmarkListDTO(Page<ClothBookmark> bookmarkPage) {
        List<BookmarkResponseDTO.BookmarkDTO> bookmarkDTOList = bookmarkPage.stream()
                .map(bookmark -> BookmarkResponseDTO.BookmarkDTO.builder()
                        .clothId(bookmark.getCloth().getId())
                        .kind(bookmark.getCloth().getKind().getName())
                        .category(bookmark.getCloth().getCategory().getName())
                        .fit(bookmark.getCloth().getFit().getName())
                        .color(bookmark.getCloth().getColor().getName())
                        .ootd(BookmarkResponseDTO.OotdInfo.builder()
                                .id(bookmark.getOotdId())
                                .imageUrl(bookmark.getOotdImageUrl())
                                .build())
                        .build())
                .collect(Collectors.toList());

        return BookmarkResponseDTO.BookmarkListDTO.builder()
                .bookmarkList(bookmarkDTOList)
                .listSize(bookmarkDTOList.size())
                .totalPage(bookmarkPage.getTotalPages())
                .totalElements(bookmarkPage.getTotalElements())
                .isFirst(bookmarkPage.isFirst())
                .isLast(bookmarkPage.isLast())
                .build();
    }

}
