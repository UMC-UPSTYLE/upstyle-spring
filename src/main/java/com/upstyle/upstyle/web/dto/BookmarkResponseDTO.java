package com.upstyle.upstyle.web.dto;

import lombok.*;

import java.util.List;

public class BookmarkResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddBookmarkResultDTO {
        private Long id;
        private Long userId;
        private Long clothId;
        private Boolean isBookmarked;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookmarkListDTO {
        private List<BookmarkResponseDTO.BookmarkDTO> bookmarkList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookmarkDTO {
        private Long clothId;
        private String kind;
        private String category;
        private String fit;
        private String color;
        private OotdInfo ootd;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OotdInfo {
        private Long id;
        private String imageUrl;
    }
}