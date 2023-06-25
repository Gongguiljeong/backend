package com.gongguiljeong.domain.user.domain.exception;




public class AlreadyExistUserException extends RuntimeException {
    private final static String MESSAGE = "이미 존재하는 회원입니다.";

    public AlreadyExistUserException() {
        super(MESSAGE);
    }
}
