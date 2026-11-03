package com.example.hospitalmanagementsystem.exception;

public class DuplicateAppointmentException extends RuntimeException {

    public DuplicateAppointmentException(String message) {
        super(message);
    }
}