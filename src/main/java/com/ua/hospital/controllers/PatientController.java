package com.ua.hospital.controllers;

import com.ua.hospital.entities.PatientEntity;
import com.ua.hospital.exceptions.PatientNotFoundException;
import com.ua.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam Long patientId) {
        try {
            PatientEntity patient = patientService.findById(patientId);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PatientEntity>> findAll() {
        List<PatientEntity> patients = patientService.findAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    /*@GetMapping("/diagnosis")
    public ResponseEntity<List<PatientEntity>> findByDiagnosis(@RequestParam String diagnosis) {
        List<PatientEntity> patients = patientService.findAllByDiagnosis(diagnosis);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<PatientEntity> save(@RequestBody PatientEntity patient) {
        PatientEntity patientSaved = patientService.save(patient);
        return patientSaved != null
                ? new ResponseEntity<>(patientSaved, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long patientId,
                                    @RequestBody PatientEntity patient) {
        try {
            PatientEntity patientUpdated = patientService.update(patientId, patient);
            return new ResponseEntity<>(patientUpdated, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long patientId) {
        try {
            patientService.delete(patientId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
