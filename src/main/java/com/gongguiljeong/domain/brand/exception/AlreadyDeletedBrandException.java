package com.gongguiljeong.domain.brand.exception;

public class AlreadyDeletedBrandException extends RuntimeException {
    private final static String MESSAGE = "이미 삭제된 브랜드입니다.";

    public AlreadyDeletedBrandException() {
        super(MESSAGE);
    }

    public AlreadyDeletedBrandException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
