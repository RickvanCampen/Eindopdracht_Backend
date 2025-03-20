package com.example.eindopdracht_backend_ipmroved.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    public AppException(String message, HttpStatus conflict) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
