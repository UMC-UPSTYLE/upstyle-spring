package com.upstyle.upstyle.service.OotdrequestService;

import com.upstyle.upstyle.domain.CodiRequest;
import com.upstyle.upstyle.domain.CodiResponse;
import com.upstyle.upstyle.web.dto.*;

public interface CodiReqCommandService {
    CodiRequest addCodiReq(CodiReqRequestDTO.AddCodiReqDTO codiReqRequestDTO);
    CodiResponse addCodiRes(CodiResRequestDTO.addCodiResDTO codiResRequestDTO, Long requestId);

}
