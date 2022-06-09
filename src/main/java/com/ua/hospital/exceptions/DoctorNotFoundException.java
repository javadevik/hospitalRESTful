package com.ua.hospital.exceptions;

public class DoctorNotFoundException extends Exception {

    public DoctorNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
