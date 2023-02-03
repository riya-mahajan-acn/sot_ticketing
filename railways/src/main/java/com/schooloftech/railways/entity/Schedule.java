package com.schooloftech.railways.entity;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="train_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private Time departure_time;


    public Schedule() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getDeparture_time() {
        return this.departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="train_id" , referencedColumnName ="id" , nullable = false)
    private Train train;

    @ManyToOne(optional=false)
    @JoinColumn(name="station_id" , referencedColumnName ="id" , nullable = false)
    private Station station;

}
