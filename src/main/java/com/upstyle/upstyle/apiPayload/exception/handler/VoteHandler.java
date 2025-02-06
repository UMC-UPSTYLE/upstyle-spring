package com.upstyle.upstyle.apiPayload.exception.handler;

import com.upstyle.upstyle.apiPayload.code.BaseErrorCode;
import com.upstyle.upstyle.apiPayload.exception.GeneralException;

public class VoteHandler extends GeneralException {
    public VoteHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
