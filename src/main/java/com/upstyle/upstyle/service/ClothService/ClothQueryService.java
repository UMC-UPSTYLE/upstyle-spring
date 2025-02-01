package com.upstyle.upstyle.service.ClothService;

import com.upstyle.upstyle.web.dto.ClothResponseDTO;

import java.util.List;

public interface ClothQueryService {

    ClothResponseDTO.ClothPreviewListDTO getClothPreviewList(Long kindId, Long categoryId, List<Long> colorId, Long fitId, int page, int size);
}
