package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "time_")
public class Time implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String startTime;
    private String endTime;

    public Time() {}

    public Time(String start_time, String end_time) {
        this.id = timeToIdTime(start_time, end_time);
        this.startTime = start_time;
        this.endTime = end_time;
    }

    private int timeToIdTime(String startTime, String endTime) {
        if (startTime.compareTo("8:30") == 0 && endTime.compareTo("10:00") == 0) return 1;
        else if (startTime.compareTo("10:15") == 0 && endTime.compareTo("11:45") == 0) return 2;
        else if (startTime.compareTo("12:00") == 0 && endTime.compareTo("13:30") == 0) return 3;
        else if (startTime.compareTo("14:00") == 0 && endTime.compareTo("15:30") == 0) return 4;
        else if (startTime.compareTo("15:45") == 0 && endTime.compareTo("17:15") == 0) return 5;
        else if (startTime.compareTo("17:30") == 0 && endTime.compareTo("19:00") == 0) return 6;
        else if (startTime.compareTo("19:15") == 0 && endTime.compareTo("20:45") == 0) return 7;
        return 0;
    }

    public int getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return startTime + " - " + endTime;
    }
}
