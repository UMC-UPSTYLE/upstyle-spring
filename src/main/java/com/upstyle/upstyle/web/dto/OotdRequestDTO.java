package com.upstyle.upstyle.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class OotdRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addOotdDTO {
        private LocalDate date;
        private List<String> imageUrls;
        private List<ClothRequestDTO> clothRequestDTOList;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClothRequestDTO {
        private Long clothId;
        private Long clothKindId;
        private Long clothCategoryId;
        private Long fitCategoryId;
        private Long colorCategoryId;
        private String additionalInfo;
    }
}
