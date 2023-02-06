package com.schooloftech.railways.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="capacity")
public class Capacity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String train_name;
    private Date departure_date;
    private int capacity = 250;

    public Capacity() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrain_name() {
        return this.train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public Date getDeparture_date() {
        return this.departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
