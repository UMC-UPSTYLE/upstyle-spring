package com.upstyle.upstyle.web.dto;

import lombok.*;

import java.util.List;

public class CodiResResponseDTO {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodiResViewDTO {
        private Long id;
        private CodiResResponseDTO.User user;
        private String body;
        private String ImageUrl;
        private List<CodiClothResponseDTO> clothResponseList;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodiClothResponseDTO {
        Long id;
        String kindName;
        String categoryName;
        String fitName;
        String colorName;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodiResPreviewDTO{
        private Long id;
        private Long userid;
        private String nickname;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodiResPreviewListDTO {
        List<CodiResResponseDTO.CodiResPreviewDTO> CodiResPreviewDTO;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User{
        private Long id;
        private String nickname;
    }
}
