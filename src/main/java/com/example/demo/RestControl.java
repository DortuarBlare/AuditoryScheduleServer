package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.repository.AuditoryDAO;
import com.example.demo.repository.GroupDAO;
import com.example.demo.repository.ScheduleDAO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("schedules")
public class RestControl {
    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private AuditoryDAO auditoryDAO;

    @Autowired
    private GroupDAO groupDAO;

    @CrossOrigin
    @GetMapping("/schedules")
    @ApiOperation("Getting a list of schedules")
    public List<Schedule> getSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        scheduleDAO.findAll().forEach(schedules::add);

        return schedules;
    }

    @CrossOrigin
    @GetMapping("/auditories")
    @ApiOperation("Getting a list of auditories")
    public List<Auditory> getAuditories() {
        List<Auditory> auditories = new ArrayList<>();
        auditoryDAO.findAll().forEach(auditories::add);

        return auditories;
    }

    @CrossOrigin
    @GetMapping("/groups")
    @ApiOperation("Getting a list of groups")
    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();
        groupDAO.findAll().forEach(groups::add);

        return groups;
    }

    @CrossOrigin
    @PostMapping("/schedules/add")
    @ApiOperation("Adding a new schedule")
    public Schedule addSchedule(String auditory, String group, int week, String day, String startTime, String endTime) {
        Auditory auditory1 = new Auditory(auditory);
        List<Auditory> auditories = new ArrayList<>();
        auditoryDAO.findAll().forEach(auditories::add);
        for (Auditory aud: auditories) {
            if (aud.getAuditory().equals(auditory))
                auditory1.setId(aud.getId());
        }
        Group group1 = new Group(group);
        List<Group> groups = new ArrayList<>();
        groupDAO.findAll().forEach(groups::add);
        for (Group gr: groups) {
            if (gr.getGroup_().equals(group))
                group1.setId(gr.getId());
        }
        Day day1 = new Day(day);
        Time time = new Time(startTime, endTime);
        Schedule schedule = new Schedule(auditory1, group1, week, day1, time);
        scheduleDAO.save(schedule);
        return schedule;
    }

    @CrossOrigin
    @PostMapping("/auditories/add")
    @ApiOperation("Adding a new auditory")
    public Auditory addAuditory(String auditoryName) {
        Auditory auditory = new Auditory(auditoryName);
        auditoryDAO.save(auditory);
        return auditory;
    }

    @CrossOrigin
    @PostMapping("/groups/add")
    @ApiOperation("Adding a new group")
    public Group addGroup(String groupName) {
        Group group = new Group(groupName);
        groupDAO.save(group);
        return group;
    }
}
