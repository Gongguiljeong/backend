package com.gongguiljeong.domain.category.exception;

public class InterestMainCategoryNotFoundException extends RuntimeException {

    private final static String MESSAGE = "존재하지 않는 관심카테고리입니다.";

    public InterestMainCategoryNotFoundException() {
        super(MESSAGE);
    }


}
