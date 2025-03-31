package com.upstyle.upstyle.apiPayload.exception.handler;

import com.upstyle.upstyle.apiPayload.code.BaseErrorCode;
import com.upstyle.upstyle.apiPayload.exception.GeneralException;

public class UserHandler extends GeneralException {
  public UserHandler(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
