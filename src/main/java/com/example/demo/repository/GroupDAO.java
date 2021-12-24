package com.example.demo.repository;

import com.example.demo.entities.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupDAO extends CrudRepository<Group, Integer> {}
