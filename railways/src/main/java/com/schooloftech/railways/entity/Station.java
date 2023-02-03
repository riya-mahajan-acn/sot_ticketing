package com.schooloftech.railways.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="station")
public class Station {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String station;


    public Station() {
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStation() {
        return this.station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @OneToMany(mappedBy = "station")
    List<Schedule> schedule;

}
