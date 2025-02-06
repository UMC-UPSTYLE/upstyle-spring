package com.upstyle.upstyle.web.dto;

import lombok.*;

import java.util.List;

public class VoteResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteDTO{
        private Long id;
        private Long userId;
        private String title;
        private String body;
        private List<VoteResponseDTO.VoteOptionDTO> optionList;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteOptionDTO {
        private Long id;
        private Long clothId;
        private String imageUrl;
        private String name;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VotePreviewListDTO {
        List<VoteResponseDTO.VotePreviewDTO> votePreviewList;
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
    public static class VotePreviewDTO {
        Long id;
        String title;
        Integer totalResponseCount;
    }
}
