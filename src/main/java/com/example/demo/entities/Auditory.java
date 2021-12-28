package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "auditory")
public class Auditory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String auditory;

    @OneToMany(mappedBy = "auditory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Schedule> schedules;

    public Auditory() {}

    public Auditory(String auditory) {
        this.auditory = auditory;
        schedules = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuditoryName() {
        return auditory;
    }

    public void setAuditoryName(String auditory) {
        this.auditory = auditory;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return auditory;
    }
}
