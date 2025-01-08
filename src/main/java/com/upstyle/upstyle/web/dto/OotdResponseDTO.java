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
        Long categoryId;
        Long fitId;
        Long colorId;
    }

}
