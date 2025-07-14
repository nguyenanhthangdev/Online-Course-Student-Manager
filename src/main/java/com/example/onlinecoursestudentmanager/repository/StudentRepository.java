package com.example.onlinecoursestudentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlinecoursestudentmanager.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
