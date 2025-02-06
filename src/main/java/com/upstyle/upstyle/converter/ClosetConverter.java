package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.web.dto.ClosetResponseDTO;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ClosetConverter {
    public static ClosetResponseDTO.ClothKindListDTO toClothKindListDTO(Long userId, List<Object[]> clothData) {
        List<ClosetResponseDTO.ClothKindDTO> clothKindList = clothData.stream()
                .map(data -> ClosetResponseDTO.ClothKindDTO.builder()
                        .kindId(((Number) data[0]).longValue())
                        .kindName((String) data[1])
                        .thumbnailUrl((String) data[2])
                        .build())
                .collect(Collectors.toList());

        return ClosetResponseDTO.ClothKindListDTO.builder()
                .userId(userId)
                .clothKindList(clothKindList)
                .build();
    }

    public static ClothResponseDTO.ClothPreviewListDTO toClothPreviewListDTO(Page<Cloth> clothPage) {
        // ClothConverter의 toClothPreviewListDTO 활용
        return ClothConverter.toClothPreviewListDTO(clothPage);
    }
}
