package com.upstyle.upstyle.service.ClosetService;

import com.upstyle.upstyle.web.dto.ClosetResponseDTO;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;

import java.util.List;

public interface ClosetQueryService {
    ClosetResponseDTO.ClothKindListDTO getClothKindList(Long userId);

    ClothResponseDTO.ClothPreviewListDTO getClothPreviewList(Long userId, Long kindId, Long categoryId, List<Long> colorId, Long fitId, int page, int size);
}
