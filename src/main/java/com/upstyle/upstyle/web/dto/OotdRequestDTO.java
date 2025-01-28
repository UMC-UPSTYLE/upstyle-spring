package com.upstyle.upstyle.web.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class OotdRequestDTO {
    @Getter
    public static class addOotdDTO {
        Long userId;
        LocalDate date;
        List<ClothRequestDTO> clothRequestDTOList;
    }

    @Getter
    public static class ClothRequestDTO{
        Long clothId;
        Long clothKindId;
        Long clothCategoryId;
        Long fitCategoryId;
        Long colorCategoryId;
    }
}
