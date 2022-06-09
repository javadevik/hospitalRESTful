package com.ua.hospital.service.impl;

import com.ua.hospital.entities.CabinetEntity;
import com.ua.hospital.entities.DoctorEntity;
import com.ua.hospital.entities.PatientEntity;
import com.ua.hospital.entities.ScheduleEntity;
import com.ua.hospital.exceptions.ScheduleNotFoundException;
import com.ua.hospital.repositories.ScheduleRepository;
import com.ua.hospital.service.CabinetStrippedService;
import com.ua.hospital.service.DoctorStrippedService;
import com.ua.hospital.service.PatientStrippedService;
import com.ua.hospital.service.ScheduleService;
import com.ua.hospital.util.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final DoctorStrippedService doctorService;
    private final CabinetStrippedService cabinetService;
    private final PatientStrippedService patientService;
    private final DateService dateService;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               DoctorStrippedService doctorService,
                               CabinetStrippedService cabinetService,
                               PatientStrippedService patientService,
                               DateService dateService) {
        this.scheduleRepository = scheduleRepository;
        this.doctorService = doctorService;
        this.cabinetService = cabinetService;
        this.patientService = patientService;
        this.dateService = dateService;
    }

    @Override
    public ScheduleEntity findById(Long id) throws ScheduleNotFoundException {
        return scheduleRepository.findById(id)
                .orElseThrow(
                        () -> new ScheduleNotFoundException("Cannot find schedule")
                );
    }

    @Override
    public List<ScheduleEntity> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleEntity save(Long doctorId, Long cabinetId, Long patientId,
                               String date, ScheduleEntity schedule) throws Exception {
        DoctorEntity doctor = doctorService.findById(doctorId);
        CabinetEntity cabinet = cabinetService.findById(cabinetId);
        PatientEntity patient = patientService.findById(patientId);

        Timestamp timestamp = dateService.parseToTimestamp(date);

        schedule.setDoctor(doctor);
        schedule.setCabinet(cabinet);
        schedule.setPatient(patient);
        schedule.setDate(timestamp);

        return scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleEntity update(Long scheduleId, Long doctorId, Long cabinetId, Long patientId,
                               String date, ScheduleEntity schedule) throws Exception {
        ScheduleEntity scheduleToUpdate = findById(scheduleId);
        DoctorEntity doctor = doctorService.findById(doctorId);
        CabinetEntity cabinet = cabinetService.findById(cabinetId);
        PatientEntity patient = patientService.findById(patientId);

        Timestamp timestamp = dateService.parseToTimestamp(date);

        scheduleToUpdate.setDoctor(doctor);
        scheduleToUpdate.setCabinet(cabinet);
        scheduleToUpdate.setPatient(patient);
        scheduleToUpdate.setDate(timestamp);
        scheduleToUpdate.setDiagnosis(schedule.getDiagnosis());

        return scheduleRepository.save(scheduleToUpdate);
    }

    @Override
    public ScheduleEntity putDiagnosis(Long scheduleId, String diagnosis) throws ScheduleNotFoundException {
        ScheduleEntity schedule = findById(scheduleId);
        schedule.setDiagnosis(diagnosis);
        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Long scheduleId) throws ScheduleNotFoundException {
        ScheduleEntity scheduleToDelete = findById(scheduleId);
        scheduleRepository.delete(scheduleToDelete);
    }

}
