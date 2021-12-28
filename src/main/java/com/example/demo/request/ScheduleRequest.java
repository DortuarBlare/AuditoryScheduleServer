package com.example.demo.request;

import com.example.demo.entities.Day;
import com.example.demo.entities.Time;

public class ScheduleRequest {
    private AuditoryRequest auditory;
    private GroupRequest group;
    private int week;
    private DayRequest day;
    private TimeRequest time;

    public AuditoryRequest getAuditory() {
        return auditory;
    }

    public void setAuditory(AuditoryRequest auditory) {
        this.auditory = auditory;
    }

    public GroupRequest getGroup() {
        return group;
    }

    public void setGroup(GroupRequest group) {
        this.group = group;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public DayRequest getDay() {
        return day;
    }

    public void setDay(DayRequest day) {
        this.day = day;
    }

    public TimeRequest getTime() {
        return time;
    }

    public void setTime(TimeRequest time) {
        this.time = time;
    }
}
