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
    public ClothResponseDTO.ClothKindListDTO getClothKindList(Long userId) {
        // 사용자별 옷 종류별 가장 최근 등록된 옷 조회
        List<Object[]> clothData = clothRepository.findLatestClothByKindAndUserId(userId);

        // DTO 변환
        return ClothConverter.toClothKindListDTO(userId, clothData);
    }

    @Override
    public ClothResponseDTO.ClothPreviewListDTO getClothPreviewList(Long userId, Long kindId, Long categoryId, Long colorId, Long fitId, int page, int size) {
        // Cloth 데이터 조회 (페이징 처리)
        Page<Cloth> clothPage = clothRepository.findClothesByFilters(userId, kindId, categoryId, colorId, fitId, PageRequest.of(page, size));

        // DTO 변환
        List<ClothResponseDTO.ClothPreviewDTO> clothPreviews = clothPage.stream()
                .map(ClothConverter::toClothPreviewDTO)
                .collect(Collectors.toList());

        return ClothResponseDTO.ClothPreviewListDTO.builder()
                .clothPreviewList(clothPreviews)
                .listSize(clothPreviews.size())
                .totalPage(clothPage.getTotalPages())
                .totalElements(clothPage.getTotalElements())
                .isFirst(clothPage.isFirst())
                .isLast(clothPage.isLast())
                .build();
    }
}
