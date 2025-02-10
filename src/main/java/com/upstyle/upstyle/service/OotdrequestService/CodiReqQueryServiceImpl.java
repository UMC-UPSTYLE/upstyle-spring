package com.upstyle.upstyle.service.OotdrequestService;

import com.upstyle.upstyle.apiPayload.code.status.ErrorStatus;
import com.upstyle.upstyle.apiPayload.exception.handler.CodiReqHandler;
import com.upstyle.upstyle.apiPayload.exception.handler.OotdHandler;
import com.upstyle.upstyle.converter.CodiReqConverter;
import com.upstyle.upstyle.converter.OotdConverter;
import com.upstyle.upstyle.converter.VoteConverter;
import com.upstyle.upstyle.domain.CodiRequest;
import com.upstyle.upstyle.domain.CodiResponse;
import com.upstyle.upstyle.domain.Ootd;
import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.repository.CodiReqRepository;
import com.upstyle.upstyle.repository.CodiResRepository;
import com.upstyle.upstyle.repository.VoteRepository;
import com.upstyle.upstyle.web.dto.CodiReqResponseDTO;
import com.upstyle.upstyle.web.dto.CodiResResponseDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodiReqQueryServiceImpl implements CodiReqQueryService {
    private final CodiReqRepository codiReqRepository;
    private final CodiResRepository codiResRepository;

    @Override
    public CodiReqResponseDTO.CodiReqPreviewListDTO getCodiReqPreviewList(int page, int size) {
        Page<CodiRequest> codiReqPage = codiReqRepository.findPagedCodiReq(PageRequest.of(page, size));
        return CodiReqConverter.toCodiReqPreviewListDTO(codiReqPage);
    }

    @Override
    public CodiReqResponseDTO.CodiReqDetailviewDTO getCodiReqDetailview(Long requestid) {
        CodiRequest codiRequest = codiReqRepository.findById(requestid)
                .orElseThrow(() -> new CodiReqHandler(ErrorStatus.CODI_REQUEST_NOT_FOUND));
        return CodiReqConverter.toCodiReqDetailDTO(codiRequest);

    }

    @Override
    public CodiResResponseDTO.CodiResViewDTO getCodiResponse(Long responseid){
        CodiResponse codiResponse = codiResRepository.findById(responseid)
                .orElseThrow(() -> new CodiReqHandler(ErrorStatus.CODI_RESPONSE_NOT_FOUND));
        return CodiReqConverter.toCodiResViewDTO(codiResponse);
    }
}
