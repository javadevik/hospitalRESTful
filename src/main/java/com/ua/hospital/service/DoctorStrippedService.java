package com.ua.hospital.service;

import com.ua.hospital.entities.DoctorEntity;
import com.ua.hospital.exceptions.DoctorNotFoundException;

public interface DoctorStrippedService {
    DoctorEntity findById(Long doctorId) throws DoctorNotFoundException;
}
