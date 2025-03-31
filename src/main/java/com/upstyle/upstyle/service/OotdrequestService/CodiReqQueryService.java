package com.upstyle.upstyle.service.OotdrequestService;

import com.upstyle.upstyle.web.dto.CodiReqResponseDTO;
import com.upstyle.upstyle.web.dto.CodiResResponseDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;

public interface CodiReqQueryService {
    CodiReqResponseDTO.CodiReqPreviewListDTO getCodiReqPreviewList(int page, int size);
    CodiReqResponseDTO.CodiReqDetailviewDTO getCodiReqDetailview(Long requestid);
    CodiResResponseDTO.CodiResViewDTO getCodiResponse(Long responseid);
}
