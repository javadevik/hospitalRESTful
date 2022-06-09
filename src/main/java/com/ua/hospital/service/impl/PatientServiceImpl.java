package com.ua.hospital.service.impl;

import com.ua.hospital.entities.PatientEntity;
import com.ua.hospital.exceptions.PatientNotFoundException;
import com.ua.hospital.repositories.PatientRepository;
import com.ua.hospital.service.PatientService;
import com.ua.hospital.service.PatientStrippedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService, PatientStrippedService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientEntity findById(Long id) throws PatientNotFoundException {
        return patientRepository.findById(id)
                .orElseThrow(
                        () -> new PatientNotFoundException("Cannot find patient")
                );
    }

    @Override
    public List<PatientEntity> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public PatientEntity save(PatientEntity patient) {
        return patientRepository.save(patient);
    }

    @Override
    public PatientEntity update(Long patientId, PatientEntity patient) throws PatientNotFoundException {
        PatientEntity patientToUpdate = findById(patientId);

        patientToUpdate.setFirstName(patient.getFirstName());
        patientToUpdate.setLastName(patient.getLastName());
        patientToUpdate.setPhone(patient.getPhone());

        return patientRepository.save(patientToUpdate);
    }

    @Override
    public void delete(Long patientId) throws PatientNotFoundException {
        PatientEntity patientToDelete = findById(patientId);
        //delete meeting
        patientRepository.delete(patientToDelete);
    }
}
