package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.*;
import com.upstyle.upstyle.web.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CodiReqConverter {
    //코디요청requestDTO 코디요청 엔티티로 변경
    public static CodiRequest toCodiReqEntity(CodiReqRequestDTO.AddCodiReqDTO request, User user){
        CodiRequest codiReq = new CodiRequest();
        codiReq.setUser(user);
        codiReq.setTitle(request.getTitle());
        codiReq.setBody(request.getBody());
        if (request.getImageUrl() != null) {
            codiReq.setImageUrl(request.getImageUrl());
        }
        return codiReq;
    }

    //코디요청 엔티티 코디요청ResponseDTO로 변경
    public static CodiReqResponseDTO.CodiReqPreviewDTO toCodiReqPreviewDTO(CodiRequest codiReq){
        return CodiReqResponseDTO.CodiReqPreviewDTO.builder()
                .id(codiReq.getId())
                .title(codiReq.getTitle())
                .responseCount(codiReq.getResponseCount())
                .build();
    }

    public static CodiReqResponseDTO.CodiReqPreviewListDTO toCodiReqPreviewListDTO(Page<CodiRequest> codiReqPage) {
        List<CodiReqResponseDTO.CodiReqPreviewDTO> codiReqPreviewList = codiReqPage.stream()
                .map(CodiReqConverter::toCodiReqPreviewDTO)
                .collect(Collectors.toList());

        return CodiReqResponseDTO.CodiReqPreviewListDTO.builder()
                .codiReqPreviewList(codiReqPreviewList)
                .listSize(codiReqPreviewList.size())
                .totalPage(codiReqPage.getTotalPages())
                .totalElements(codiReqPage.getTotalElements())
                .isFirst(codiReqPage.isFirst())
                .isLast(codiReqPage.isLast())
                .build();
    }

    public static CodiResResponseDTO.CodiResViewDTO toCodiResViewDTO(CodiResponse codiRes){
        //아이디, 바디, 이미지url, clothResponseLIst, 닉네임
        List<CodiResResponseDTO.CodiClothResponseDTO> clothList = codiRes.getCodiResponseClothList().stream()
                .map(CodiResponseCloth -> {
                    Cloth cloth = CodiResponseCloth.getCloth();
                    return CodiResResponseDTO.CodiClothResponseDTO.builder()
                            .id(cloth.getId())
                            .kindName(cloth.getKind().getName())
                            .categoryName(cloth.getCategory().getName())
                            .fitName(cloth.getFit().getName())
                            .colorName(cloth.getColor().getName())
                            .build();
                }).collect(Collectors.toList());

        return new CodiResResponseDTO.CodiResViewDTO().builder()
                .id(codiRes.getId())
                .user(CodiResResponseDTO.User.builder()
                        .id(codiRes.getUser().getId())
                        .nickname(codiRes.getUser().getNickname())
                        .build())
                .body(codiRes.getBody())
                .ImageUrl(Optional.ofNullable(codiRes.getImageUrl()).orElse(null))
                .clothResponseList(clothList)
                .build();
    }


    public static CodiResponse toCodiResEntity(CodiResRequestDTO.addCodiResDTO request, CodiRequest codiReq, User user){
        CodiResponse newCodiRes = new CodiResponse();
        if (request.getImageUrl() != null) {
            newCodiRes.setImageUrl(request.getImageUrl());
        }
        newCodiRes.setBody(request.getBody());
        newCodiRes.setUser(user);
        newCodiRes.setRequest(codiReq);
        return newCodiRes;
    }

    public static CodiReqResponseDTO.CodiReqDetailviewDTO toCodiReqDetailDTO(CodiRequest Codireq){
        List<CodiReqResponseDTO.CodiResPreviewDTO> CodiResList = Codireq.getCodiResponseList().stream()
                .map(CodiResponse -> {
                    return CodiReqResponseDTO.CodiResPreviewDTO.builder()
                            .id(CodiResponse.getId())
                            .user(CodiReqResponseDTO.User.builder()
                                    .id(CodiResponse.getUser().getId())
                                    .nickname(CodiResponse.getUser().getNickname())
                                    .build())
                            .build();
                }).collect(Collectors.toList());

        return CodiReqResponseDTO.CodiReqDetailviewDTO.builder()
                .id(Codireq.getId())
                .title(Codireq.getTitle())
                .body(Codireq.getBody())
                .ImageUrl(Optional.ofNullable(Codireq.getImageUrl()).orElse(null))
                .user(CodiReqResponseDTO.User.builder()
                        .id(Codireq.getUser().getId())
                        .nickname(Codireq.getUser().getNickname())
                        .build())
                .codiResPreviewList(CodiResList)
                .build();

    }
}
