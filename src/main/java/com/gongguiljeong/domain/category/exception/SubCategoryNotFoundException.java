package com.gongguiljeong.domain.category.exception;

public class SubCategoryNotFoundException extends RuntimeException {

    private final static String MESSAGE = "존재하지 않는 서브 카테고리입니다.";

    public SubCategoryNotFoundException() {
        super(MESSAGE);
    }


}
