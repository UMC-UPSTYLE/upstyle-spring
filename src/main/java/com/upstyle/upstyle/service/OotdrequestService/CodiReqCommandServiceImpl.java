package com.upstyle.upstyle.service.OotdrequestService;

import com.upstyle.upstyle.apiPayload.code.status.ErrorStatus;
import com.upstyle.upstyle.apiPayload.exception.handler.ClothHandler;
import com.upstyle.upstyle.apiPayload.exception.handler.CodiReqHandler;
import com.upstyle.upstyle.apiPayload.exception.handler.UserHandler;
import com.upstyle.upstyle.apiPayload.exception.handler.VoteHandler;
import com.upstyle.upstyle.converter.CodiReqConverter;
import com.upstyle.upstyle.converter.OotdConverter;
import com.upstyle.upstyle.converter.VoteConverter;
import com.upstyle.upstyle.domain.*;
import com.upstyle.upstyle.domain.mapping.CodiResponseCloth;
import com.upstyle.upstyle.domain.mapping.OotdCloth;
import com.upstyle.upstyle.repository.*;
import com.upstyle.upstyle.web.dto.CodiReqRequestDTO;
import com.upstyle.upstyle.web.dto.CodiResRequestDTO;
import com.upstyle.upstyle.web.dto.CodiResResponseDTO;
import com.upstyle.upstyle.web.dto.OotdRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CodiReqCommandServiceImpl implements CodiReqCommandService {
    private final CodiReqRepository codiReqRepository;
    private final UserRepository userRepository;
    private final CodiResRepository codiResRepository;
    private final ClothRepository clothRepository;
    private final CodiResponseClothRepository codiResponseClothRepository;

    @Override
    @Transactional
    public CodiRequest addCodiReq(CodiReqRequestDTO.AddCodiReqDTO codiReqRequestDTO) {
        User user = userRepository.findById(codiReqRequestDTO.getUserId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        CodiRequest newCodiRequest = CodiReqConverter.toCodiReqEntity(codiReqRequestDTO, user);

        codiReqRepository.save(newCodiRequest);
        return newCodiRequest;
    }

    @Override
    @Transactional
    public CodiResponse addCodiRes(CodiResRequestDTO.addCodiResDTO codiResRequestDTO, Long requestId){
        // User 조회
        User user = userRepository.findById(codiResRequestDTO.getUserId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        // 코디요청글 조회
        CodiRequest codiRequest = codiReqRepository.findById(requestId)
                .orElseThrow(() -> new CodiReqHandler(ErrorStatus.VOTE_OPTION_NOT_FOUND));

        CodiResponse newCodiRes = CodiReqConverter.toCodiResEntity(codiResRequestDTO,codiRequest, user);
        codiResRepository.save(newCodiRes);

        List<CodiResponseCloth> CodiResClothList = Optional.ofNullable(codiResRequestDTO.getClothRequestDTOList())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(request -> {
                    Cloth cloth = clothRepository.findById(request.getClothId())
                            .orElseThrow(() -> new ClothHandler(ErrorStatus.CLOTH_ID_NOT_FOUND));
                    return SaveOCodiResponseCloth(newCodiRes, cloth);
                }).collect(Collectors.toList());

        newCodiRes.setCodiResponseClothList(CodiResClothList);


        codiRequest.setResponseCount(codiRequest.getResponseCount() + 1);
        codiReqRepository.save(codiRequest);

        return codiResRepository.save(newCodiRes);
    }

    private CodiResponseCloth SaveOCodiResponseCloth(CodiResponse codiRes, Cloth cloth) {
        CodiResponseCloth codiResponseCloth = new CodiResponseCloth();
        codiResponseCloth.setResponse(codiRes);
        codiResponseCloth.setCloth(cloth);
        return codiResponseClothRepository.save(codiResponseCloth);
    }
}
