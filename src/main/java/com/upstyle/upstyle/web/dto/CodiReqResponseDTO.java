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
        private String nickname;
        private String Body;
        private String ImageUrl;
        private List<CodiReqResponseDTO.CodiResPreviewDTO> codiResPreviewList;
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

}
