package com.ua.hospital.service.impl;

import com.ua.hospital.entities.DoctorEntity;
import com.ua.hospital.exceptions.DoctorNotFoundException;
import com.ua.hospital.repositories.DoctorRepository;
import com.ua.hospital.service.DoctorService;
import com.ua.hospital.service.DoctorStrippedService;
import com.ua.hospital.util.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService, DoctorStrippedService {

    private final DoctorRepository doctorRepository;
    private final DateService dateService;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DateService dateService) {
        this.doctorRepository = doctorRepository;
        this.dateService = dateService;
    }

    @Override
    public DoctorEntity findById(Long id) throws DoctorNotFoundException {
        return doctorRepository.findById(id)
                .orElseThrow(
                        () -> new DoctorNotFoundException("Cannot find doctor")
                );
    }

    @Override
    public List<DoctorEntity> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public List<DoctorEntity> findAllFreeAt(String date) {
        Timestamp timestamp = dateService.parseToTimestamp(date);
        return doctorRepository.findAllFreeAt(timestamp);
    }

    @Override
    public List<DoctorEntity> findAllFreeAtSpecialist(String date, String specialistType) {
        Timestamp timestamp = dateService.parseToTimestamp(date);
        return doctorRepository.findAllFreeAtWithSpecialistType(timestamp, specialistType);
    }

    @Override
    public DoctorEntity save(DoctorEntity doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public DoctorEntity update(Long doctorId, DoctorEntity doctor) throws DoctorNotFoundException {
        DoctorEntity doctorToUpdate = findById(doctorId);
        doctorToUpdate.setFirstName(doctor.getFirstName());
        doctorToUpdate.setLastName(doctor.getLastName());
        doctorToUpdate.setSpecialistType(doctor.getSpecialistType());
        return doctorRepository.save(doctorToUpdate);
    }

    @Override
    public void delete(Long doctorId) throws DoctorNotFoundException {
        DoctorEntity doctorToDelete = findById(doctorId);
        //delete meeting
        doctorRepository.delete(doctorToDelete);
    }

}
