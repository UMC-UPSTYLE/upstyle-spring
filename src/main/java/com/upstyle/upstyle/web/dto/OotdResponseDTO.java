package com.upstyle.upstyle.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OotdResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addOotdResultDTO{
        Long id;
        Long userId;
        LocalDate date;
        List<String> imageUrls;
        List<ClothResponseDTO> clothResponseList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClothResponseDTO {
        Long id;
        Long kindId;
        Long categoryId;
        Long fitId;
        Long colorId;
        String additionalInfo;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CalendarResponseDTO{
        Long userId;
        List<OotdPreviewDTO> ootdPreviewList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OotdPreviewDTO{
        Long id;
        LocalDate date;
        String imageUrl;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OotdDTO{
        Long id;
        User user;
        LocalDate date;
        List<String> imageUrls;
        List<ClothDTO> clothList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClothDTO {
        Long id;
        Long kindId;
        String kindName;
        Long categoryId;
        String categoryName;
        Long fitId;
        String fitName;
        Long colorId;
        String colorName;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User{
        Long id;
        String nickname;
    }

}
