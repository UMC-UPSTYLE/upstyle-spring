package com.upstyle.upstyle.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ClothResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClothKindListDTO {
        Long userId;
        String userName;
        List<ClothKindDTO> clothKindList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClothKindDTO {
        Long id;
        OotdDTO ootd;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClothPreviewListDTO {
        List<ClothPreviewDTO> clothPreviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClothPreviewDTO {
        Long id;
        Long kindId;
        Long categoryId;
        Long fitId;
        Long colorId;
        OotdDTO ootd;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OotdDTO {
        Long id;
        String imageUrl;
    }
}
