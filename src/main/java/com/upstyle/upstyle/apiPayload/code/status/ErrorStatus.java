package com.upstyle.upstyle.apiPayload.code.status;

import com.upstyle.upstyle.apiPayload.code.BaseErrorCode;
import com.upstyle.upstyle.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "USER4002", "닉네임은 필수 입니다."),

    //ootd 관련 에러
    OOTD_NOT_FOUND(HttpStatus.BAD_REQUEST, "OOTD4001", "ootd가 없습니다."),

    //옷 관련 에러
    CLOTH_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "CLOTH4001", "옷 카테고리가 없습니다."),
    CLOTH_COLOR_NOT_FOUND(HttpStatus.BAD_REQUEST, "CLOTH4002", "옷 색깔이 없습니다."),
    CLOTH_FIT_NOT_FOUND(HttpStatus.BAD_REQUEST, "CLOTH4003", "옷 핏이 없습니다."),
    CLOTH_KIND_NOT_FOUND(HttpStatus.BAD_REQUEST, "CLOTH4004", "옷 종류가 없습니다."),
    CLOTH_ID_NOT_FOUND(HttpStatus.BAD_REQUEST, "CLOTH4005", "옷이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}