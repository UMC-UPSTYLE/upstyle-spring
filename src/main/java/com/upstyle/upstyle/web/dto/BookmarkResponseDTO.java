package com.upstyle.upstyle.web.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponseDTO {
    private Long id;
    private Long userId;
    private Long clothId;
}