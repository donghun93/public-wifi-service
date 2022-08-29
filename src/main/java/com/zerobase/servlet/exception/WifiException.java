package com.zerobase.servlet.exception;

import lombok.Getter;

@Getter
public class WifiException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    public WifiException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
