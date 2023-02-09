package com.schooloftech.railways.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name= "stations")
public class Stations {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String trainGroup;
    private String stationName;
    public Stations(){

    }
    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getTrainGroup() {return trainGroup;}

    public void setTrainGroup(String trainGroup) {this.trainGroup = trainGroup;}

    public String getStationName() {return stationName;}

    public void setStationName(String trainName) {this.stationName = stationName;}

}
