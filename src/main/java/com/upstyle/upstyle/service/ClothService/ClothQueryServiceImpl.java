package com.upstyle.upstyle.service.ClothService;

import com.upstyle.upstyle.converter.ClothConverter;
import com.upstyle.upstyle.repository.ClothRepository;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import lombok.RequiredArgsConstructor;
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
}
