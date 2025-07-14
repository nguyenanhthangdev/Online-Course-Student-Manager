package com.example.onlinecoursestudentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlinecoursestudentmanager.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
