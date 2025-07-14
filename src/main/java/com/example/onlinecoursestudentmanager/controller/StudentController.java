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

import com.example.onlinecoursestudentmanager.entity.Student;
import com.example.onlinecoursestudentmanager.service.StudentService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public String getAllStudent(Model model) {
        model.addAttribute("students", studentService.getAllStudent());
        model.addAttribute("active", "student");
        return "students/all-student-page";
    }

    @GetMapping("/add-student")
    public String addStudent(Model model) {
        model.addAttribute("active", "student");
        model.addAttribute("student", new Student());
        return "students/add-student-page";
    }

    @PostMapping("/save-student")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes,
                            Model model) {
        if (bindingResult.hasErrors()) {
            return "students/add-student-page";
        }
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message", "Thêm sinh viên thành công!");
        return "redirect:/students";
    }

    @GetMapping("/edit-student/{id}")
    public String editStudent(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional optionalStudent = studentService.getStudentById(id);
        if (optionalStudent.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sinh viên với id: " + id);
            return "redirect:/students";
        }
        model.addAttribute("active", "student");
        model.addAttribute("student", optionalStudent.get());
        return "students/edit-student-page";
    }

    @PostMapping("/update-student")
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "students/edit-student-page";
        }              
        
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message", "Thay đổi thông tin sinh viên thành công!");
        return "redirect:/students";
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        Optional optionalStudent = studentService.getStudentById(id);
        if (optionalStudent.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sinh viên với id: " + id);
            return "redirect:/students";
        }

        studentService.deleteStudentById(id);
        model.addAttribute("active", "student");
        redirectAttributes.addFlashAttribute("message", "Xóa sinh viên thành công!");
        return "redirect:/students";
    }
}