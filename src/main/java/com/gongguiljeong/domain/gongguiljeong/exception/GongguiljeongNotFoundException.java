package com.gongguiljeong.domain.gongguiljeong.exception;

public class GongguiljeongNotFoundException extends RuntimeException{
    private final static String MESSAGE = "존재하지 않는 공구일정입니다.";

    public GongguiljeongNotFoundException() {
        super(MESSAGE);
    }
}
