package com.ua.hospital.repositories;

import com.ua.hospital.entities.CabinetEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CabinetRepository extends CrudRepository<CabinetEntity, Long> {
    List<CabinetEntity> findAll();

    @Query(value = "SELECT * FROM cabinets FULL OUTER JOIN schedules s on cabinets.id = s.cabinet_id WHERE date < ?1 OR date IS NULL", nativeQuery = true)
    List<CabinetEntity> findAllFreeAt(Timestamp date);

    @Query(value = "SELECT * FROM cabinets FULL OUTER JOIN schedules s on cabinets.id = s.cabinet_id WHERE (date < ?1 OR date IS NULL) AND cabinet_type = ?2", nativeQuery = true)
    List<CabinetEntity> findAllFreeAtCabinet(Timestamp date, String cabinetType);
}
