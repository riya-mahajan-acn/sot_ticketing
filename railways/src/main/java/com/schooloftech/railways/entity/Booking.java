package com.schooloftech.railways.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int booking_id;
    @OneToMany
    @JoinColumn(name="users", referencedColumnName = "id")
    private int customer_id;
    private Date booking_date;
    private String departure_st;
    private Date departure_date;
    private Time departure_time;
    @OneToMany
    @JoinColumn(name="train_fare", referencedColumnName = "fare")
    private int train_fare;
    private int number_of_tickets;

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public String getDeparture_st() {
        return departure_st;
    }

    public void setDeparture_st(String departure_st) {
        this.departure_st = departure_st;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public int getTrain_fare() {
        return train_fare;
    }

    public void setTrain_fare(int train_fare) {
        this.train_fare = train_fare;
    }

    public int getNumber_of_tickets() {
        return number_of_tickets;
    }

    public void setNumber_of_tickets(int number_of_tickets) {
        this.number_of_tickets = number_of_tickets;
    }

    
}
