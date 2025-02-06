package com.upstyle.upstyle.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class VoteRequestDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddVoteDTO {
        private Long userId;
        private String title;
        private String body;
        private List<VoteRequestDTO.AddVoteOptionDTO> optionList;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddVoteOptionDTO {
        private Long clothId;
        private String imageUrl;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseVoteDTO {
        private Long userId;
        private Long optionId;
    }
}
