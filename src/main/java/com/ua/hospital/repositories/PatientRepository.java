package com.ua.hospital.repositories;

import com.ua.hospital.entities.PatientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<PatientEntity, Long> {
    List<PatientEntity> findAll();
}
