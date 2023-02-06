package com.schooloftech.railways.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "stations")
public class Stations {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String train_group;
    private String train_name;
    public Stations(){

    }
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTrain_group() {return train_group;}

    public void setTrain_group(String train_group) {this.train_group = train_group;}

    public String getTrain_name() {return train_name;}

    public void setTrain_name(String train_name) {this.train_name = train_name;}

}
