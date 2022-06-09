package com.ua.hospital.repositories;

import com.ua.hospital.entities.ScheduleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAll();
}
