package com.gongguiljeong.domain.brand.exception;

public class BrandNotFoundException extends RuntimeException {
    static final String MESSAGE = "존재하는 브랜드가 없습니다.";

    public BrandNotFoundException() {
        super(MESSAGE);
    }

}
