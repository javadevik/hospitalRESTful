package com.ua.hospital.service;

import com.ua.hospital.entities.PatientEntity;
import com.ua.hospital.exceptions.PatientNotFoundException;

public interface PatientStrippedService {
    PatientEntity findById(Long patientId) throws PatientNotFoundException;
}
