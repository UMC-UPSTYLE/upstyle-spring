package com.upstyle.upstyle.apiPayload.exception.handler;

import com.upstyle.upstyle.apiPayload.code.BaseErrorCode;
import com.upstyle.upstyle.apiPayload.exception.GeneralException;

public class ClothHandler extends GeneralException {
    public ClothHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
