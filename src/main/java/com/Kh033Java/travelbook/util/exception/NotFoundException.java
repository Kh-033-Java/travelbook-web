package com.Kh033Java.travelbook.util.exception;

import org.springframework.lang.NonNull;

/**
 * Exception that is thrown when an object is not found in database.
 */
public class NotFoundException extends RuntimeException {
    /**
     * Constructor with parameter.
     * @param msg error message
     */
    public NotFoundException(@NonNull final String msg) {
        super(msg);
    }
}
