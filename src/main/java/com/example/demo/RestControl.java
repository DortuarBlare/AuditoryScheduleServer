package com.example.demo;

import com.example.demo.entities.Auditory;
import com.example.demo.entities.Group;
import com.example.demo.entities.Schedule;
import com.example.demo.repository.AuditoryDAO;
import com.example.demo.repository.GroupDAO;
import com.example.demo.repository.ScheduleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Schedule> getSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        scheduleDAO.findAll().forEach(schedules::add);

        return schedules;
    }

    @CrossOrigin
    @GetMapping("/auditories")
    public List<Auditory> getAuditories() {
        List<Auditory> auditories = new ArrayList<>();
        auditoryDAO.findAll().forEach(auditories::add);

        return auditories;
    }

    @CrossOrigin
    @GetMapping("/groups")
    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();
        groupDAO.findAll().forEach(groups::add);

        return groups;
    }
}
