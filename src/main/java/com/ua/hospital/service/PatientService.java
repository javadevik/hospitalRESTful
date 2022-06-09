package com.ua.hospital.service;

import com.ua.hospital.entities.PatientEntity;
import com.ua.hospital.exceptions.PatientNotFoundException;

import java.util.List;

public interface PatientService {
    PatientEntity findById(Long id) throws PatientNotFoundException;

    List<PatientEntity> findAll();

    PatientEntity save(PatientEntity patient);

    PatientEntity update(Long patientId, PatientEntity patient) throws PatientNotFoundException;

    void delete(Long patientId) throws PatientNotFoundException;
}
