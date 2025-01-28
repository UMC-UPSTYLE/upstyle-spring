package com.upstyle.upstyle.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ClosetResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClothKindListDTO {
        private Long userId;
        private String userName;
        private List<ClothKindDTO> clothKindList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClothKindDTO {
        private Long kindId;
        private String kindName;
        private String thumbnailUrl;
    }

}
