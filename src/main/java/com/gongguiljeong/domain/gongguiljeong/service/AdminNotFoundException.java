package com.gongguiljeong.domain.gongguiljeong.service;

public class AdminNotFoundException extends RuntimeException {
    private final static String MESSAGE = "존재하지 어드민입니다.";

    public AdminNotFoundException() {
        super(MESSAGE);
    }
}
