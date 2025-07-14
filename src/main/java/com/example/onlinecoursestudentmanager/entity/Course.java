package com.example.onlinecoursestudentmanager.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên khóa học không được để trống")
    private String name;

    @NotBlank(message = "Mô tả khóa học không được để trống")
    private String description;

    @NotNull(message = "Số giờ học không được để trống")
    @Min(value = 1, message = "Số giờ học không hợp lệ")
    private int duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày tạo khóa không được để trống")
    private LocalDate createAt;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }
}
