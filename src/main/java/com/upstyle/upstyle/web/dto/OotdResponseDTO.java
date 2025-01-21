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
    public static class CalenderResponseDTO{
        Long userId;
        List<DateOotdDTO> dateOotdDTOList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateOotdDTO{
        Long ootdId;
        LocalDateTime createdAt;
        String imageUrl;
    }

}
