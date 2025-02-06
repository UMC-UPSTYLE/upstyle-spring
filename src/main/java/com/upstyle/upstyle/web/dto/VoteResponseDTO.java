package com.upstyle.upstyle.web.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class VoteResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteDTO{
        private Long id;
        private VoteResponseDTO.User user;
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
        private Integer responseCount;
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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User{
        Long id;
        String nickname;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseVoteResultDTO {
        List<VoteResponseDTO.VoteOptionDTO> voteResultList;
    }
}
