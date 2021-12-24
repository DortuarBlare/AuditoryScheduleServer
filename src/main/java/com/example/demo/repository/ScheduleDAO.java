package com.example.demo.repository;

import com.example.demo.entities.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleDAO extends CrudRepository<Schedule, Integer> {}