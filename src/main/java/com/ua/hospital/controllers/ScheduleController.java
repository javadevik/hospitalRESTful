package com.ua.hospital.controllers;

import com.ua.hospital.entities.ScheduleEntity;
import com.ua.hospital.exceptions.ScheduleNotFoundException;
import com.ua.hospital.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@CrossOrigin(origins = "http://localhost:3000")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam Long scheduleId) {
        try {
            ScheduleEntity scheduleFound = scheduleService.findById(scheduleId);
            return new ResponseEntity<>(scheduleFound, HttpStatus.OK);
        } catch (ScheduleNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ScheduleEntity>> findAll() {
        List<ScheduleEntity> schedules = scheduleService.findAll();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestParam Long doctorId,
                                  @RequestParam Long cabinetId,
                                  @RequestParam Long patientId,
                                  @RequestParam String date,
                                  @RequestBody ScheduleEntity schedule) {
        try {
            ScheduleEntity scheduleSaved = scheduleService.save(doctorId, cabinetId, patientId, date, schedule);
            return new ResponseEntity<>(scheduleSaved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long scheduleId,
                                   @RequestParam Long doctorId,
                                   @RequestParam Long cabinetId,
                                   @RequestParam Long patientId,
                                   @RequestParam String date,
                                   @RequestBody ScheduleEntity schedule) {
        try {
            ScheduleEntity scheduleUpdated = scheduleService.update(scheduleId,
                    doctorId, cabinetId, patientId, date, schedule);
            return new ResponseEntity<>(scheduleUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> putDiagnosis(@RequestParam Long scheduleId,
                                          @RequestParam String diagnosis) {
        try {
            ScheduleEntity schedule = scheduleService.putDiagnosis(scheduleId, diagnosis);
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (ScheduleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long scheduleId) {
        try {
            scheduleService.delete(scheduleId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ScheduleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
