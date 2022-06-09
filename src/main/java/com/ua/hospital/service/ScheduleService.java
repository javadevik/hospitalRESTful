package com.ua.hospital.service;

import com.ua.hospital.entities.ScheduleEntity;
import com.ua.hospital.exceptions.ScheduleNotFoundException;

import java.util.List;

public interface ScheduleService {
    ScheduleEntity findById(Long id) throws ScheduleNotFoundException;

    List<ScheduleEntity> findAll();

    ScheduleEntity save(Long doctorId, Long cabinetId, Long patientId,
                        String date, ScheduleEntity schedule) throws Exception;

    ScheduleEntity update(Long scheduleId, Long doctorId, Long cabinetId, Long patientId,
                          String date, ScheduleEntity schedule) throws Exception;

    ScheduleEntity putDiagnosis(Long scheduleId, String diagnosis) throws ScheduleNotFoundException;

    void delete(Long scheduleId) throws ScheduleNotFoundException;
}
