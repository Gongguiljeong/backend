package com.gongguiljeong.domain.common.domain.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class GongguiljeongException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public GongguiljeongException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
