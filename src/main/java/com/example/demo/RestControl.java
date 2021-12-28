package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.repository.AuditoryDAO;
import com.example.demo.repository.GroupDAO;
import com.example.demo.repository.ScheduleDAO;
import com.example.demo.request.AuditoryRequest;
import com.example.demo.request.GroupRequest;
import com.example.demo.request.ScheduleRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @GetMapping
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
    @PostMapping
    @ApiOperation("Adding a new schedule")
    public Schedule addSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        Auditory possiblyExistingAuditory = new Auditory(scheduleRequest.getAuditory().getAuditoryName());
        List<Auditory> auditories = new ArrayList<>();
        auditoryDAO.findAll().forEach(auditories::add);
        for (Auditory aud: auditories) {
            if (aud.getAuditoryName().equals(scheduleRequest.getAuditory().getAuditoryName())) {
                possiblyExistingAuditory = aud;
                break;
            }
        }

        Group possiblyExistingGroup = new Group(scheduleRequest.getGroup().getGroupName());
        List<Group> groups = new ArrayList<>();
        groupDAO.findAll().forEach(groups::add);
        for (Group gr: groups) {
            if (gr.getGroupName().equals(scheduleRequest.getGroup().getGroupName())) {
                possiblyExistingGroup = gr;
                break;
            }
        }

        Day day = new Day(scheduleRequest.getDay().getDay());
        Time time = new Time(scheduleRequest.getTime().getStartTime(), scheduleRequest.getTime().getEndTime());

        Schedule schedule = new Schedule(
                possiblyExistingAuditory,
                possiblyExistingGroup,
                scheduleRequest.getWeek(),
                day,
                time
        );

        return scheduleDAO.save(schedule);
    }

    @CrossOrigin
    @PostMapping("/auditories")
    @ApiOperation("Adding a new auditory")
    public Auditory addAuditory(@RequestBody AuditoryRequest auditoryRequest) {
        return auditoryDAO.save(new Auditory(auditoryRequest.getAuditoryName()));
    }

    @CrossOrigin
    @PostMapping("/groups")
    @ApiOperation("Adding a new group")
    public Group addGroup(@RequestBody GroupRequest groupRequest) {
        return groupDAO.save(new Group(groupRequest.getGroupName()));
    }

    @CrossOrigin
    @PutMapping("/auditories/{auditoryID}")
    @ApiOperation("Updating auditory")
    public ResponseEntity updateAuditory(@PathVariable int auditoryID, @RequestBody AuditoryRequest auditoryRequest) {
        try {
            List<Auditory> auditories = new ArrayList<>();
            auditoryDAO.findAll().forEach(auditories::add);

            for (Auditory au : auditories) {
                if (au.getId() == auditoryID) {
                    au.setAuditoryName(auditoryRequest.getAuditoryName());
                    auditoryDAO.save(au);
                    return ResponseEntity.status(HttpStatus.OK).build();
                }
            }

            throw new Exception("Didn't find such auditory");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @CrossOrigin
    @PutMapping("/groups/{groupID}")
    @ApiOperation("Updating group")
    public ResponseEntity updateGroup(@PathVariable int groupID, @RequestBody GroupRequest groupRequest){
        try {
            List<Group> groups = new ArrayList<>();
            groupDAO.findAll().forEach(groups::add);

            for (Group gr : groups) {
                if (gr.getId() == groupID) {
                    gr.setGroupName(groupRequest.getGroupName());
                    groupDAO.save(gr);
                    return ResponseEntity.status(HttpStatus.OK).build();
                }
            }
            throw new Exception("Didn't find such group");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }

    }

    @CrossOrigin
    @PutMapping("/{scheduleID}")
    @ApiOperation("Updating schedule")
    public ResponseEntity updateSchedule(@PathVariable int scheduleID, @RequestBody ScheduleRequest scheduleRequest){
        try {
            List<Schedule> schedules = new ArrayList<>();
            scheduleDAO.findAll().forEach(schedules::add);

            for (Schedule sc : schedules) {
                if (sc.getId() == scheduleID) {
                    Auditory newAuditory = new Auditory(scheduleRequest.getAuditory().getAuditoryName());
                    sc.setAuditory(newAuditory);
                    List<Auditory> auditories = new ArrayList<>();
                    auditoryDAO.findAll().forEach(auditories::add);
                    for (Auditory au : auditories) {
                        if (au.getAuditoryName().equals(scheduleRequest.getAuditory().getAuditoryName())) {
                            sc.setAuditory(au);
                            break;
                        }
                    }

                    Group newGroup = new Group(scheduleRequest.getGroup().getGroupName());
                    sc.setGroup(newGroup);
                    List<Group> groups = new ArrayList<>();
                    groupDAO.findAll().forEach(groups::add);
                    for (Group gr : groups) {
                        if (gr.getGroupName().equals(scheduleRequest.getGroup().getGroupName())) {
                            sc.setGroup(gr);
                            break;
                        }
                    }

                    sc.setWeek(scheduleRequest.getWeek());

                    sc.setDay(new Day(scheduleRequest.getDay().getDay()));

                    sc.setTime(new Time(scheduleRequest.getTime().getStartTime(),
                            scheduleRequest.getTime().getEndTime()));

                    scheduleDAO.save(sc);
                    return ResponseEntity.status(HttpStatus.OK).build();
                }
            }
            throw new Exception("Didn't find such schedule");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }

    }

    @Transactional
    @CrossOrigin
    @DeleteMapping("/{scheduleID}")
    @ApiOperation("Removing schedule")
    public ResponseEntity deleteSchedule(@PathVariable int scheduleID) {
        try {
            scheduleDAO.deleteById(scheduleID);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @Transactional
    @CrossOrigin
    @DeleteMapping("/auditories/{auditoryID}")
    @ApiOperation("Removing auditory")
    public ResponseEntity deleteAuditory(@PathVariable int auditoryID) {
        try {
            auditoryDAO.deleteById(auditoryID);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @Transactional
    @CrossOrigin
    @DeleteMapping("/groups/{groupID}")
    @ApiOperation("Removing group")
    public ResponseEntity deleteGroup(@PathVariable int groupID) {
        try {
            groupDAO.deleteById(groupID);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }
}