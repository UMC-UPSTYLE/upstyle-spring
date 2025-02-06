package com.upstyle.upstyle.service.ClosetService;

import com.upstyle.upstyle.converter.ClosetConverter;
import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.repository.ClothRepository;
import com.upstyle.upstyle.web.dto.ClosetResponseDTO;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClosetQueryServiceImpl implements ClosetQueryService {
    private final ClothRepository clothRepository;

    @Override
    public ClosetResponseDTO.ClothKindListDTO getClothKindList(Long userId) {
        List<Object[]> clothData = clothRepository.findLatestClothByKindAndUserId(userId);
        return ClosetConverter.toClothKindListDTO(userId, clothData);
    }

    @Override
    public ClothResponseDTO.ClothPreviewListDTO getClothPreviewList(Long userId, Long kindId, Long categoryId, List<Long> colorId, Long fitId, int page, int size) {
        Page<Cloth> clothPage = clothRepository.findClothesByFilters(userId, kindId, categoryId, colorId, fitId, PageRequest.of(page, size));
        return ClosetConverter.toClothPreviewListDTO(clothPage);
    }
}
