package com.ua.hospital.service;

import com.ua.hospital.entities.DoctorEntity;
import com.ua.hospital.exceptions.DoctorNotFoundException;

import java.util.List;

public interface DoctorService {
    DoctorEntity findById(Long id) throws DoctorNotFoundException;

    List<DoctorEntity> findAll();

    List<DoctorEntity> findAllFreeAt(String date);

    List<DoctorEntity> findAllFreeAtSpecialist(String date, String specialistType);

    DoctorEntity save(DoctorEntity doctor);

    DoctorEntity update(Long doctorId, DoctorEntity doctor) throws DoctorNotFoundException;

    void delete(Long doctorId) throws DoctorNotFoundException;
}
