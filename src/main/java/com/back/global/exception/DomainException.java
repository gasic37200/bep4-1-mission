package com.back.global.exception;

import lombok.Getter;

@Getter
// 업무 규칙을 어겼을 때 던질 예외
public class DomainException extends RuntimeException {
    private final String resultCode;
    private final String msg;

    public DomainException(String resultCode, String msg) {
        super(resultCode + " : " + msg);
        this.resultCode = resultCode;
        this.msg = msg;
    }
}