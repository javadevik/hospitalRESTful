package com.ua.hospital.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.hospital.models.CabinetType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cabinets")
public class CabinetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer number;
    private Integer floor;
    @Enumerated(value = EnumType.STRING)
    private CabinetType cabinetType;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cabinet")
    private List<ScheduleEntity> schedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public CabinetType getCabinetType() {
        return cabinetType;
    }

    public void setCabinetType(CabinetType cabinetType) {
        this.cabinetType = cabinetType;
    }

    public List<ScheduleEntity> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleEntity> schedule) {
        this.schedule = schedule;
    }
}
