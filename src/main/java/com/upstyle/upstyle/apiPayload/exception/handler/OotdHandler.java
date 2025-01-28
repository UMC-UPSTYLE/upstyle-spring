package com.upstyle.upstyle.apiPayload.exception.handler;

import com.upstyle.upstyle.apiPayload.code.BaseErrorCode;
import com.upstyle.upstyle.apiPayload.exception.GeneralException;

public class OotdHandler extends GeneralException {
    public OotdHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
