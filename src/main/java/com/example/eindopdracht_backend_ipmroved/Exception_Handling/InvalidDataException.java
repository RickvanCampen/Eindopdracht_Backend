package com.example.eindopdracht_backend_ipmroved.Exception_Handling;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}