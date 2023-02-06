package com.schooloftech.railways.entity;

import jakarta.persistence.*;

@Entity
@Table(name="train_fare")
public class TrainFare {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String category;
    private int fare;

    public TrainFare(){

    }
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    public int getFare() {return fare;}

    public void setFare(int fare) {this.fare = fare;}

}
