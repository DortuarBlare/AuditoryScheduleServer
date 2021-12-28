package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name = "day")
public class Day implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String day;

    public Day() {}

    public Day(String day) {
        this.id = dayToIdDay(day);
        this.day = day;
    }

    private int dayToIdDay(String day) {
        if (day.compareTo("Понедельник") == 0) return 1;
        else if (day.compareTo("Вторник") == 0) return 2;
        else if (day.compareTo("Среда") == 0) return 3;
        else if (day.compareTo("Четверг") == 0) return 4;
        else if (day.compareTo("Пятница") == 0) return 5;
        else if (day.compareTo("Суббота") == 0) return 6;
        else if (day.compareTo("Воскресенье") == 0) return 7;
        return 0;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return day;
    }
}
