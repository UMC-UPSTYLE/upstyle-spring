package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ClothConverter {

    public static ClothResponseDTO.ClothKindDTO toClothKindDTO(Object[] data) {
        return ClothResponseDTO.ClothKindDTO.builder()
                .id(((Number) data[0]).longValue()) // kindId
                .imageUrl((String) data[3])        // imageUrl
                .build();
    }

    public static ClothResponseDTO.ClothKindListDTO toClothKindListDTO(Long userId, List<Object[]> data) {
        List<ClothResponseDTO.ClothKindDTO> clothKindList = data.stream()
                .map(ClothConverter::toClothKindDTO)
                .collect(Collectors.toList());

        return ClothResponseDTO.ClothKindListDTO.builder()
                .userId(userId)
                .clothKindList(clothKindList)
                .build();
    }

}
