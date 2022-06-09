package com.ua.hospital.service;

import com.ua.hospital.entities.CabinetEntity;
import com.ua.hospital.exceptions.CabinetNotFoundException;

public interface CabinetStrippedService {
    CabinetEntity findById(Long cabinetId) throws CabinetNotFoundException;
}
