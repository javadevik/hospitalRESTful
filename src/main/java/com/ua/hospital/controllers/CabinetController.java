package com.ua.hospital.controllers;

import com.ua.hospital.entities.CabinetEntity;
import com.ua.hospital.exceptions.CabinetNotFoundException;
import com.ua.hospital.service.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabinets")
@CrossOrigin(origins = "http://localhost:3000")
public class CabinetController {

    private final CabinetService cabinetService;

    @Autowired
    public CabinetController(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam Long cabinetId) {
        try {
            CabinetEntity cabinetFound = cabinetService.findById(cabinetId);
            return new ResponseEntity<>(cabinetFound, HttpStatus.OK);
        } catch (CabinetNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CabinetEntity>> findAll() {
        List<CabinetEntity> cabinets = cabinetService.findAll();
        return new ResponseEntity<>(cabinets, HttpStatus.OK);
    }

    @GetMapping("/free/at")
    public ResponseEntity<List<CabinetEntity>> findAllFreeAt(@RequestParam String date) {
        List<CabinetEntity> cabinets = cabinetService.findAllFreeAt(date);
        return new ResponseEntity<>(cabinets, HttpStatus.OK);
    }

    @GetMapping("/free/at/cabinet")
    public ResponseEntity<List<CabinetEntity>> findAllFreeAtCabinet(@RequestParam String date,
                                                                    @RequestParam String cabinetType) {
        List<CabinetEntity> cabinets = cabinetService.findAllFreeAtCabinet(date, cabinetType);
        return new ResponseEntity<>(cabinets, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CabinetEntity> save(@RequestBody CabinetEntity cabinet) {
        CabinetEntity cabinetSaved = cabinetService.save(cabinet);
        return cabinetSaved != null
                ? new ResponseEntity<>(cabinetSaved, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long cabinetId,
                                    @RequestBody CabinetEntity cabinet) {
        try {
            CabinetEntity cabinetUpdated = cabinetService.update(cabinetId, cabinet);
            return new ResponseEntity<>(cabinetUpdated, HttpStatus.OK);
        } catch (CabinetNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long cabinetId) {
        try {
            cabinetService.delete(cabinetId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CabinetNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
