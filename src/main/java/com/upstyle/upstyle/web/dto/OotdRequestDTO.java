package com.upstyle.upstyle.web.dto;

import lombok.Getter;

import java.util.List;

public class OotdRequestDTO {
    @Getter
    public static class addOotdDTO {
        Long userId;
        List<ClothRequestDTO> ClothRequestDTOList;
    }

    @Getter
    public static class ClothRequestDTO{
        Long clothKindId;
        Long clothCategoryId;
        Long fitCategoryId;
        Long colorCategoryId;
    }
}
