package com.upstyle.upstyle.service.ClosetService;

import com.upstyle.upstyle.web.dto.ClosetResponseDTO;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;

public interface ClosetQueryService {
    ClosetResponseDTO.ClothKindListDTO getClothKindList(Long userId);

    ClothResponseDTO.ClothPreviewListDTO getClothPreviewList(Long userId, Long kindId, Long categoryId, Long colorId, Long fitId, int page, int size);
}
