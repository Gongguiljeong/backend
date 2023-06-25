package com.gongguiljeong.domain.category.domain.exception;

public class MainCategoryNotFoundException extends RuntimeException {

    private final static String MESSAGE = "존재하지 않는 메인카테고리입니다.";

    public MainCategoryNotFoundException() {
        super(MESSAGE);
    }


}
