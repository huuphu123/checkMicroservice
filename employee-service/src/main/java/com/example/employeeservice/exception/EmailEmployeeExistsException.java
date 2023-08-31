package com.example.employeeservice.exception;

public class EmailEmployeeExistsException extends RuntimeException {
    String message;
    public EmailEmployeeExistsException(String message) {
        super(message);
    }
}
