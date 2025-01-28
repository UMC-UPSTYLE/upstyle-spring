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
        String kindName;
        Long categoryId;
        String categoryName;
        Long fitId;
        String fitName;
        Long colorId;
        String colorName;
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
