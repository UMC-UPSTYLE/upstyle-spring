package com.upstyle.upstyle.service.ClothService;

import com.upstyle.upstyle.converter.ClothConverter;
import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.repository.ClothRepository;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClothQueryServiceImpl implements ClothQueryService{

    private final ClothRepository clothRepository;

    @Override
    public ClothResponseDTO.ClothPreviewListDTO getClothPreviewList(Long kindId, Long categoryId, Long colorId, Long fitId, int page, int size) {
        Page<Cloth> clothPage = clothRepository.findClothesByFilters(null, kindId, categoryId, colorId, fitId, PageRequest.of(page, size));
        return ClothConverter.toClothPreviewListDTO(clothPage);
    }
}
