package com.example.onlinecoursestudentmanager.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.onlinecoursestudentmanager.entity.Course;
import com.example.onlinecoursestudentmanager.service.CourseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping()
    public String getAllCourse(Model model) {
        model.addAttribute("active", "course");
        model.addAttribute("courses", courseService.getAllCourse());
        return "courses/all-course-page";
    }

    @GetMapping("/add-course")
    public String addCourse(Model model) {
        model.addAttribute("active", "course");
        model.addAttribute("course", new Course());
        return "courses/add-course-page";
    }

    @PostMapping("/save-course")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes,
                            Model model) {
        if (bindingResult.hasErrors()) {
            return "courses/add-course-page";
        }
        courseService.save(course);
        redirectAttributes.addFlashAttribute("message", "Thêm khóa học thành công!");
        return "redirect:/courses";
    }

    @GetMapping("/edit-course/{id}")
    public String editCourse(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional optionalCourse = courseService.getCourseById(id);
        if (optionalCourse.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy khóa học với id: " + id);
            return "redirect:/courses";
        }
        model.addAttribute("active", "course");
        model.addAttribute("course", optionalCourse.get());
        return "courses/edit-course-page";
    }

    @PostMapping("/update-course")
    public String updateCourse(@Valid @ModelAttribute("course") Course course,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "courses/edit-course-page";
        }              
        
        courseService.save(course);
        redirectAttributes.addFlashAttribute("message", "Thay đổi thông tin khóa học thành công!");
        return "redirect:/courses";
    }

    @GetMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        Optional optionalCourse = courseService.getCourseById(id);
        if (optionalCourse.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy khóa học với id: " + id);
            return "redirect:/courses";
        }

        courseService.deleteCourseById(id);
        model.addAttribute("active", "course");
        redirectAttributes.addFlashAttribute("message", "Xóa khóa học thành công!");
        return "redirect:/courses";
    }
}
