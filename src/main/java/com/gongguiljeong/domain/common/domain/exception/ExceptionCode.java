package com.gongguiljeong.domain.common.domain.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ExceptionCode {

    //Common
    SUCCESS(HttpStatus.OK.value(), HttpStatus.OK, "성공"),
    UN_AUTHENTICATION(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN, "권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "찾을 수 없습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다."),


    //User
    USER_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXIST(1002, HttpStatus.BAD_REQUEST, "이미 존재하는 사용자입니다."),

    //Brand
    BRAND_NOT_FOUND(2001, HttpStatus.NOT_FOUND, "브랜드를 찾을 수 없습니다."),
    BRAND_ALREADY_EXIST(2002, HttpStatus.BAD_REQUEST, "이미 존재하는 브랜드입니다."),

    //ADMIN
    ADMIN_NOT_FOUND(3001, HttpStatus.NOT_FOUND, "관리자를 찾을 수 없습니다."),
    ADMIN_ALREADY_EXIST(3002, HttpStatus.BAD_REQUEST, "이미 존재하는 관리자입니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;

    ExceptionCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
