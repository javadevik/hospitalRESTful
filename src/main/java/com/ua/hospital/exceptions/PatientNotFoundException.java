package com.ua.hospital.exceptions;

public class PatientNotFoundException extends Exception {

    public PatientNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
