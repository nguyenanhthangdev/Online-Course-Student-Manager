package com.example.onlinecoursestudentmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlinecoursestudentmanager.entity.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    public boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    public List<Enrollment> findByStudentId(Long id);

    public List<Enrollment> findByCourseId(Long id);

}
