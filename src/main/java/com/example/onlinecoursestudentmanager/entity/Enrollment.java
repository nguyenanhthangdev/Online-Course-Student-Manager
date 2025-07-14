package com.example.onlinecoursestudentmanager.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Phải chọn sinh viên")
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @NotNull(message = "Phải chọn khóa học")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày đăng ký không được để trống")
    @PastOrPresent(message = "Ngày đăng ký không thể ở tương lai")
    private LocalDate enrolledAt;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrolledAt() {
        return this.enrolledAt;
    }

    public void setEnrolledAt(LocalDate enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}
