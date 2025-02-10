package com.upstyle.upstyle.apiPayload.exception.handler;

import com.upstyle.upstyle.apiPayload.code.BaseErrorCode;
import com.upstyle.upstyle.apiPayload.exception.GeneralException;

public class CodiReqHandler  extends GeneralException {
    public CodiReqHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
