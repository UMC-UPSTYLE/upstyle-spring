package com.upstyle.upstyle.web.dto;

import lombok.*;

import java.util.List;

public class CodiReqResponseDTO {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodiReqPreviewDTO {
        private Long id;
        private String title;
        private Integer responseCount;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodiReqPreviewListDTO {
        List<CodiReqResponseDTO.CodiReqPreviewDTO> codiReqPreviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodiReqDetailviewDTO{
        private Long id;
        private String title;
        private String body;
        private String ImageUrl;
        private CodiReqResponseDTO.User user;
        private List<CodiReqResponseDTO.CodiResPreviewDTO> codiResPreviewList;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodiResPreviewDTO{
        private Long id;
        private CodiReqResponseDTO.User user;
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
