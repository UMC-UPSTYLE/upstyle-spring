package com.upstyle.upstyle.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class CodiResRequestDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addCodiResDTO {
        private String body;
        private LocalDate date;
        private String imageUrl;
        private List<CodiResRequestDTO.resClothRequestDTO> clothRequestDTOList;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class resClothRequestDTO {
        private Long clothId;
    }
}
