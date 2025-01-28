package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.OotdImage;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClothConverter {

    // ClothPreviewDTO 변환
    public static ClothResponseDTO.ClothPreviewDTO toClothPreviewDTO(Cloth cloth) {
        // OOTD 이미지 가져오기
        String ootdImageUrl = getOotdImageUrl(cloth);

        return ClothResponseDTO.ClothPreviewDTO.builder()
                .id(cloth.getId())
                .kindId(cloth.getKind().getId())
                .kindName(cloth.getKind().getName())
                .categoryId(cloth.getCategory().getId())
                .categoryName(cloth.getCategory().getName())
                .fitId(cloth.getFit().getId())
                .fitName(cloth.getFit().getName())
                .colorId(cloth.getColor().getId())
                .colorName(cloth.getColor().getName())
                .ootd(ClothResponseDTO.OotdDTO.builder()
                        .id(getOotdId(cloth))
                        .imageUrl(ootdImageUrl)
                        .build())
                .build();
    }

    // ClothPreviewListDTO 변환
    public static ClothResponseDTO.ClothPreviewListDTO toClothPreviewListDTO(Page<Cloth> clothPage) {
        List<ClothResponseDTO.ClothPreviewDTO> clothPreviewList = clothPage.stream()
                .map(ClothConverter::toClothPreviewDTO)
                .collect(Collectors.toList());

        return ClothResponseDTO.ClothPreviewListDTO.builder()
                .clothPreviewList(clothPreviewList)
                .listSize(clothPreviewList.size())
                .totalPage(clothPage.getTotalPages())
                .totalElements(clothPage.getTotalElements())
                .isFirst(clothPage.isFirst())
                .isLast(clothPage.isLast())
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
