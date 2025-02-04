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
    public static class UploadVoteDTO {
        private Long userId;
        private String title;
        private String body;
        private List<VoteOptionDTO> optionList;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteOptionDTO {
        private Long clothId;
        private String image_url;
        private String name;
    }
}
