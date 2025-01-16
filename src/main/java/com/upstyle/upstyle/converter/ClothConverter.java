package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.OotdImage;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClothConverter {

    // ClothKindDTO 변환
    public static ClothResponseDTO.ClothKindDTO toClothKindDTO(Object[] data) {
        return ClothResponseDTO.ClothKindDTO.builder()
                .id(((Number) data[0]).longValue()) // kindId
                .ootd(ClothResponseDTO.OotdDTO.builder()
                        .id(((Number) data[2]).longValue()) // OOTD ID
                        .imageUrl((String) data[3])        // OOTD Image URL
                        .build())
                .build();
    }

    public static ClothResponseDTO.ClothKindListDTO toClothKindListDTO(Long userId, List<Object[]> data) {
        List<ClothResponseDTO.ClothKindDTO> clothKindList = data.stream()
                .map(ClothConverter::toClothKindDTO)
                .collect(Collectors.toList());

        return ClothResponseDTO.ClothKindListDTO.builder()
                .userId(userId)
                .clothKindList(clothKindList)
                .build();
    }

    // ClothPreviewDTO 변환
    public static ClothResponseDTO.ClothPreviewDTO toClothPreviewDTO(Cloth cloth) {
        // OOTD 이미지 가져오기
        String ootdImageUrl = getOotdImageUrl(cloth);

        return ClothResponseDTO.ClothPreviewDTO.builder()
                .id(cloth.getId())
                .kindId(cloth.getKind().getId())
                .categoryId(cloth.getCategory().getId())
                .colorId(cloth.getColor().getId())
                .fitId(cloth.getFit().getId())
                .ootd(ClothResponseDTO.OotdDTO.builder()
                        .id(getOotdId(cloth))
                        .imageUrl(ootdImageUrl)
                        .build())
                .build();
    }

    // OOTD ID 가져오기
    private static Long getOotdId(Cloth cloth) {
        return cloth.getOotdClothList().stream()
                .map(ootdCloth -> ootdCloth.getOotd().getId())
                .findFirst() // 가장 첫 번째 OOTD 사용
                .orElse(null); // 없을 경우 null 반환
    }

    // OOTD 이미지 URL 가져오기
    private static String getOotdImageUrl(Cloth cloth) {
        Optional<OotdImage> ootdImage = cloth.getOotdClothList().stream()
                .map(ootdCloth -> ootdCloth.getOotd()) // OOTD 추출
                .flatMap(ootd -> ootd.getOotdImageList().stream()) // OOTD 이미지 리스트 추출
                .findFirst(); // 가장 첫 번째 이미지 사용

        return ootdImage.map(OotdImage::getImageUrl).orElse(null); // 없을 경우 null 반환
    }

}
