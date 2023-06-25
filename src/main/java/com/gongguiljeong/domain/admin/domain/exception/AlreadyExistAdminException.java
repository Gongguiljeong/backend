package com.gongguiljeong.domain.admin.domain.exception;

public class AlreadyExistAdminException extends RuntimeException {

    private final static String MESSAGE = "이미 존재하는 아이디입니다.";

    public AlreadyExistAdminException() {
        super(MESSAGE);
    }

    public AlreadyExistAdminException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
