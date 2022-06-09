package com.ua.hospital.controllers;

import com.ua.hospital.entities.DoctorEntity;
import com.ua.hospital.exceptions.DoctorNotFoundException;
import com.ua.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam Long doctorId) {
        try {
            DoctorEntity doctorFound = doctorService.findById(doctorId);
            return new ResponseEntity<>(doctorFound, HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<DoctorEntity>> findAll() {
        List<DoctorEntity> doctors = doctorService.findAll();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/free/at")
    public ResponseEntity<List<DoctorEntity>> findAllFreeAt(@RequestParam String date) {
        List<DoctorEntity> doctors = doctorService.findAllFreeAt(date);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/free/at/specialist")
    public ResponseEntity<List<DoctorEntity>> findAllFreeAtSpecialist(@RequestParam String date,
                                                                      @RequestParam String specialistType) {
        List<DoctorEntity> doctors = doctorService.findAllFreeAtSpecialist(date, specialistType);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DoctorEntity> save(@RequestBody DoctorEntity doctor) {
        DoctorEntity doctorSaved = doctorService.save(doctor);
        return doctorSaved != null
                ? new ResponseEntity<>(doctorSaved, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long doctorId,
                                    @RequestBody DoctorEntity doctor) {
        try {
            DoctorEntity doctorUpdated = doctorService.update(doctorId, doctor);
            return new ResponseEntity<>(doctorUpdated, HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long doctorId) {
        try {
            doctorService.delete(doctorId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

}
