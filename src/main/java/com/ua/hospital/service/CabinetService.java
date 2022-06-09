package com.ua.hospital.service;

import com.ua.hospital.entities.CabinetEntity;
import com.ua.hospital.exceptions.CabinetNotFoundException;

import java.util.List;

public interface CabinetService {
    CabinetEntity findById(Long id) throws CabinetNotFoundException;

    List<CabinetEntity> findAll();

    List<CabinetEntity> findAllFreeAt(String date);

    List<CabinetEntity> findAllFreeAtCabinet(String date, String cabinetType);

    CabinetEntity save(CabinetEntity cabinet);

    CabinetEntity update(Long cabinetId, CabinetEntity cabinet) throws CabinetNotFoundException;

    void delete(Long cabinetId) throws CabinetNotFoundException;
}
