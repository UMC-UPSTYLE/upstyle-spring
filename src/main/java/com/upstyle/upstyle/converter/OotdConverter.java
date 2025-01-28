package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.*;
import com.upstyle.upstyle.domain.mapping.OotdCloth;
import com.upstyle.upstyle.web.dto.OotdRequestDTO;
import com.upstyle.upstyle.web.dto.OotdResponseDTO;

import java.util.List;
import java.util.stream.Collectors;


public class OotdConverter {
    //addOotdDTO를 Ootd 엔티티로 변환.
    public static Ootd toOotd(OotdRequestDTO.addOotdDTO request, User user) {
        Ootd newOotd = new Ootd();
        newOotd.setUser(user);
        newOotd.setDate(request.getDate());
        return newOotd;
    }

    //ClothRequestDTO를 cloth엔티티로 변환. fit, color등의 속성 매칭은 service 딴에서 수행.
    public static Cloth toCloth(OotdRequestDTO.ClothRequestDTO request, User user) {
        Cloth newCloth = new Cloth();
        newCloth.setUser(user);
        newCloth.setAdditionalInfo(request.getAdditionalInfo());
        return newCloth;
    }

    //ootd 엔티티를 addOotdResultDTO로 변환.
    public static OotdResponseDTO.addOotdResultDTO toAddOotdResultDTO(Ootd ootd) {
        List<OotdResponseDTO.ClothResponseDTO> clothResponseDTOList = ootd.getOotdClothList().stream()
                .map(ootdCloth -> {
                    Cloth cloth = ootdCloth.getCloth();
                    return OotdResponseDTO.ClothResponseDTO.builder()
                            .id(cloth.getId())
                            .categoryId(cloth.getCategory().getId())
                            .fitId(cloth.getFit().getId())
                            .colorId(cloth.getColor().getId())
                            .build();
                }).collect(Collectors.toList());

        return OotdResponseDTO.addOotdResultDTO.builder()
                .id(ootd.getId())
                .userId(ootd.getUser().getId())
                .clothResponseList(clothResponseDTOList)
                .date(ootd.getDate())
                .build();
    }

    // imageUrl과 Ootd를 이용해 OotdImage를 생성
    public static OotdImage toOotdImage(String imageUrl, Ootd ootd) {
        return OotdImage.builder()
                .imageUrl(imageUrl)
                .ootd(ootd)
                .build();
    }

    public static OotdResponseDTO.CalendarResponseDTO toCalendarResponseDTO(Long userid, List<OotdResponseDTO.OotdPreviewDTO> ootdPreviewList) {
        return OotdResponseDTO.CalendarResponseDTO.builder()
                .userId(userid)
                .ootdPreviewList(ootdPreviewList)
                .build();
    }

    public static OotdResponseDTO.OotdDTO toOotdDTO(Ootd ootd) {
        // ClothDTO 리스트 변환
        List<OotdResponseDTO.ClothDTO> clothList = ootd.getOotdClothList().stream()
                .map(ootdCloth -> {
                    Cloth cloth = ootdCloth.getCloth();
                    return OotdResponseDTO.ClothDTO.builder()
                            .id(cloth.getId())
                            .kindId(cloth.getKind().getId())
                            .kindName(cloth.getKind().getName())
                            .categoryId(cloth.getCategory().getId())
                            .categoryName(cloth.getCategory().getName())
                            .fitId(cloth.getFit().getId())
                            .fitName(cloth.getFit().getName())
                            .colorId(cloth.getColor().getId())
                            .colorName(cloth.getColor().getName())
                            .build();
                }).collect(Collectors.toList());

        // UserDTO 생성
        OotdResponseDTO.User userDTO = OotdResponseDTO.User.builder()
                .id(ootd.getUser().getId())
                .nickname(ootd.getUser().getNickname())
                .build();

        // OotdDTO 생성
        return OotdResponseDTO.OotdDTO.builder()
                .id(ootd.getId())
                .user(userDTO)
                .date(ootd.getDate())
                .imageUrl(ootd.getOotdImageList().isEmpty() ? null : ootd.getOotdImageList().get(0).getImageUrl())
                .clothList(clothList)
                .build();
    }

}
