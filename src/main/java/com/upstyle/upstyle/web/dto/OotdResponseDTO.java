package com.upstyle.upstyle.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class OotdResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addOotdResultDTO{
        Long ootdId;
        Long userId;
        List<ClothResponseDTO> clothResponseDTOList;
        LocalDateTime createdAt;

    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClothResponseDTO {
        Long clothId;
        Long kindId;
        Long categoryId;
        Long fitId;
        Long colorId;
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
        String imageUrl;
        Long clothKindId;
        Long clothCategoryId;
        Long clothFitId;
        Long clothColorId;
    }

}
