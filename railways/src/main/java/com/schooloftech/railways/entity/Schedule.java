package com.schooloftech.railways.entity;

import java.sql.Time;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String train_group;

    private String train_name;

    private String departure_st;

    private String arrival_st;

    private Time departure_time;

    public Schedule() {
    }


    public Schedule(String train_group, String train_name, String departure_st, String arrival_st, LocalTime localTime) {
        this.train_group = train_group;
        this.train_name = train_name;
        this.departure_st = departure_st;
        this.arrival_st = arrival_st;
        this.departure_time = Time.valueOf(localTime);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrain_group() {
        return this.train_group;
    }

    public void setTrain_group(String train_group) {
        this.train_group = train_group;
    }

    public String getTrain_name() {
        return this.train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getDeparture_st() {
        return this.departure_st;
    }

    public void setDeparture_st(String departure_st) {
        this.departure_st = departure_st;
    }

    public String getArrival_st() {
        return this.arrival_st;
    }

    public void setArrival_st(String arrival_st) {
        this.arrival_st = arrival_st;
    }

    public Time getDeparture_time() {
        return this.departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

}
