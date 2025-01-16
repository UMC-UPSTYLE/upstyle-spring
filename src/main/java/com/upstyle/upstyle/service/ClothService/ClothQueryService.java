package com.upstyle.upstyle.service.ClothService;

import com.upstyle.upstyle.web.dto.ClothResponseDTO;

public interface ClothQueryService {

    ClothResponseDTO.ClothKindListDTO getClothKindList(Long userId);

}
