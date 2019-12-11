package com.Kh033Java.travelbook.exception;

public class BadFileException extends RuntimeException {
    public BadFileException(String message) {
        super(message);
    }

    public BadFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
