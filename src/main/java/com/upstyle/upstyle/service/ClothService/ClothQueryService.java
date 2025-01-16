package com.upstyle.upstyle.service.ClothService;

import com.upstyle.upstyle.web.dto.ClothResponseDTO;

public interface ClothQueryService {

    ClothResponseDTO.ClothKindListDTO getClothKindList(Long userId);

    ClothResponseDTO.ClothPreviewListDTO getClothPreviewList(Long userId, Long kindId, Long categoryId, Long colorId, Long fitId, int page, int size);
}
