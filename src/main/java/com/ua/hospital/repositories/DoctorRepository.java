package com.ua.hospital.repositories;

import com.ua.hospital.entities.DoctorEntity;
import com.ua.hospital.models.SpecialistType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface DoctorRepository extends CrudRepository<DoctorEntity, Long> {
    List<DoctorEntity> findAll();

    @Query(value = "SELECT * FROM doctors FULL OUTER JOIN schedules s on doctors.id = s.doctor_id WHERE date < ?1 OR date IS NULL", nativeQuery = true)
    List<DoctorEntity> findAllFreeAt(Timestamp date);

    @Query(value = "SELECT * FROM doctors FULL OUTER JOIN schedules s on doctors.id = s.doctor_id WHERE (date < ?1 OR date IS NULL) AND specialist_type = ?2", nativeQuery = true)
    List<DoctorEntity> findAllFreeAtWithSpecialistType(Timestamp date, String type);

}
