package com.example.hospitalmanagementsystem.exception;

public class PatientNotfoundException extends RuntimeException
{
    public PatientNotfoundException(String message)
    {
        super(message);
    }
}
