package com.example.onlinecoursestudentmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinecoursestudentmanager.entity.Course;
import com.example.onlinecoursestudentmanager.entity.Enrollment;
import com.example.onlinecoursestudentmanager.repository.EnrollmentRepository;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    public boolean existsByStudentIdAndCourseId(Long studentId, Long courseId) {
        return enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
    }

    public void deleteEnrollmentById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    public List<Enrollment> getEnrollmentsByStudentId(Long id) {
        return enrollmentRepository.findByStudentId(id);
    }

    public List<Enrollment> getEnrollmentsByCourseId(Long id) {
        return enrollmentRepository.findByCourseId(id);
    }
}
