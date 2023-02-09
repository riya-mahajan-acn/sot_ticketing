package com.schooloftech.railways.form;


import java.time.LocalDate;
import java.time.LocalTime;

import com.schooloftech.railways.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BookingForm {

    private String departing_station;
    private String arrival_station;
    private LocalDate departure_date;


    public LocalTime getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(LocalTime departure_time) {
        this.departure_time = departure_time;
    }

    private LocalTime departure_time;
    private int number_of_people;
    private double train_fare;

    // Getters and setters for the fields
    public String getdeparting_station() {
        return departing_station;
    }

    public void setdeparting_station(String departing_station) {
        this.departing_station = departing_station;
    }

    public String getarrival_station() {
        return arrival_station;
    }

    public void setarrival_station(String arrival_station) {
        this.arrival_station = arrival_station;
    }

    public LocalDate getdeparture_date() {
        return departure_date;
    }

    public void setdeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    public int getnumber_of_people() {
        return number_of_people;
    }

    public void setnumber_of_people(int number_of_people) {
        this.number_of_people = number_of_people;
    }
    public double gettrain_fare(){
        return train_fare;
    }

    public void settrain_fare(double train_fare){
        this.train_fare = train_fare;
    }
}