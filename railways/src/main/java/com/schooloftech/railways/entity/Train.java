package com.schooloftech.railways.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="trains")
public class Train {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String train_name;

    public Train() {
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

    @OneToMany(mappedBy = "train")
    List<Schedule> schedule;


}
