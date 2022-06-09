package com.ua.hospital.service.impl;

import com.ua.hospital.entities.CabinetEntity;
import com.ua.hospital.exceptions.CabinetNotFoundException;
import com.ua.hospital.repositories.CabinetRepository;
import com.ua.hospital.service.CabinetService;
import com.ua.hospital.service.CabinetStrippedService;
import com.ua.hospital.util.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CabinetServiceImpl implements CabinetService, CabinetStrippedService {

    private final CabinetRepository cabinetRepository;
    private final DateService dateService;

    @Autowired
    public CabinetServiceImpl(CabinetRepository cabinetRepository, DateService dateService) {
        this.cabinetRepository = cabinetRepository;
        this.dateService = dateService;
    }

    @Override
    public CabinetEntity findById(Long id) throws CabinetNotFoundException {
        return cabinetRepository.findById(id)
                .orElseThrow(
                        () -> new CabinetNotFoundException("Cannot find cabinet")
                );
    }

    @Override
    public List<CabinetEntity> findAll() {
        return cabinetRepository.findAll();
    }

    @Override
    public List<CabinetEntity> findAllFreeAt(String date) {
        Timestamp timestamp = dateService.parseToTimestamp(date);
        return cabinetRepository.findAllFreeAt(timestamp);
    }

    @Override
    public List<CabinetEntity> findAllFreeAtCabinet(String date, String cabinetType) {
        Timestamp timestamp = dateService.parseToTimestamp(date);
        return cabinetRepository.findAllFreeAtCabinet(timestamp, cabinetType);
    }

    @Override
    public CabinetEntity save(CabinetEntity cabinet) {
        return cabinetRepository.save(cabinet);
    }

    @Override
    public CabinetEntity update(Long cabinetId, CabinetEntity cabinet) throws CabinetNotFoundException {
        CabinetEntity cabinetToUpdate = findById(cabinetId);
        cabinetToUpdate.setNumber(cabinet.getNumber());
        cabinetToUpdate.setFloor(cabinet.getFloor());
        cabinetToUpdate.setCabinetType(cabinet.getCabinetType());
        return cabinetRepository.save(cabinetToUpdate);
    }

    @Override
    public void delete(Long cabinetId) throws CabinetNotFoundException {
        CabinetEntity cabinetToDelete = findById(cabinetId);
        //delete meeting
        cabinetRepository.delete(cabinetToDelete);
    }
}
