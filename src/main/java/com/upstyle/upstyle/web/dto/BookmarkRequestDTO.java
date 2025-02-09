package com.upstyle.upstyle.web.dto;

import lombok.*;

public class BookmarkRequestDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddBookmarkDTO {
        private Long userId;
        private Long clothId;
    }
}