package com.example.demo.repository;

import com.example.demo.entities.Auditory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoryDAO extends CrudRepository<Auditory, Integer> {}
