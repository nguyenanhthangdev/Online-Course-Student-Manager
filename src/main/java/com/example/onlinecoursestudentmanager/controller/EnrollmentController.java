package com.example.onlinecoursestudentmanager.controller;

import java.util.List;
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
import com.example.onlinecoursestudentmanager.entity.Enrollment;
import com.example.onlinecoursestudentmanager.service.CourseService;
import com.example.onlinecoursestudentmanager.service.EnrollmentService;
import com.example.onlinecoursestudentmanager.service.StudentService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @GetMapping()
    public String allEnrollment(Model model) {
        model.addAttribute("active", "enrollment");
        model.addAttribute("enrollments", enrollmentService.getAllEnrollment());
        model.addAttribute("students", studentService.getAllStudent());
        model.addAttribute("courses", courseService.getAllCourse());
        return "enrollments/all-enrollment-page";
    }

    @GetMapping("/add-enrollment")
    public String addEnrollment(Model model) {
        model.addAttribute("active", "enrollment");
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("students", studentService.getAllStudent());
        model.addAttribute("courses", courseService.getAllCourse());
        return "enrollments/add-enrollment-page";
    }

    @PostMapping("save-enrollment")
    public String saveEnrollment(@Valid @ModelAttribute("enrollment") Enrollment enrollment,
                                   BindingResult bindingResult , RedirectAttributes redirectAttributes, Model model) {

        boolean alreadyExists = enrollmentService.existsByStudentIdAndCourseId(enrollment.getStudent().getId(), enrollment.getCourse().getId());
        if (alreadyExists) {
            bindingResult.reject("duplicate", "Sinh viên đã đăng ký khóa học này rồi.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudent());
            model.addAttribute("courses", courseService.getAllCourse());
            return "enrollments/add-enrollment-page";
        }

        enrollmentService.saveEnrollment(enrollment);
        redirectAttributes.addFlashAttribute("message", "Đăng kí khóa học thành công!");
        return "redirect:/enrollments";
    }

    @GetMapping("/edit-enrollment/{id}")
    public String editEnrollment(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        Optional enrollmentOptional = enrollmentService.getEnrollmentById(id);
        if (enrollmentOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy bản ghi đăng ký với ID: " + id);
            return "redirect:/enrollments";
        }

        model.addAttribute("active", "enrollment");
        model.addAttribute("students", studentService.getAllStudent());
        model.addAttribute("courses", courseService.getAllCourse());
        model.addAttribute("enrollment", enrollmentOptional.get());
        return "enrollments/edit-enrollment-page";
    }

    @PostMapping("/update-enrollment")
    public String updateEnrollment(@Valid @ModelAttribute Enrollment enrollment, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes, Model model) {
        boolean alreadyExists = enrollmentService.existsByStudentIdAndCourseId(enrollment.getStudent().getId(), enrollment.getCourse().getId());
        if (alreadyExists) {
            bindingResult.reject("duplicate", "Sinh viên đã đăng ký khóa học này rồi.");
        }
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudent());
            model.addAttribute("courses", courseService.getAllCourse());
            return "/enrollments/edit-enrollment-page";
        }

        enrollmentService.saveEnrollment(enrollment);
        redirectAttributes.addFlashAttribute("message", "Thay đổi thông tin bản ghi đăng kí thành công!");
        return "redirect:/enrollments";
    }

    @GetMapping("/delete-enrollment/{id}")
    public String deleteEnrollment(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        Optional optionalEnrollment = enrollmentService.getEnrollmentById(id);
        if (optionalEnrollment.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm bản ghi đăng kí với id: " + id);
            return "redirect:/enrollments";
        }

        enrollmentService.deleteEnrollmentById(id);
        model.addAttribute("active", "enrollment");
        redirectAttributes.addFlashAttribute("message", "Xóa bản ghi đăng kí thành công!");
        return "redirect:/enrollments";
    }

    @GetMapping("/filter-by-student/{id}")
    public String filterByStudent(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        Optional optionalStudent = studentService.getStudentById(id);
        if (optionalStudent.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sinh viên!");
            return "redirect:/enrollments";
        }

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(id);
        model.addAttribute("active", "enrollment");
        model.addAttribute("s", optionalStudent.get());
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("students", studentService.getAllStudent());
        model.addAttribute("courses", courseService.getAllCourse());
        return "enrollments/filter-by-student-page";
    }

    @GetMapping("/filter-by-course/{id}")
    public String filterByCourse(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        Optional optionalCourse = courseService.getCourseById(id);
        if (optionalCourse.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy khóa học!");
            return "redirect:/enrollments";
        }

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(id);
        model.addAttribute("active", "enrollment");
        model.addAttribute("c", optionalCourse.get());
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("students", studentService.getAllStudent());
        model.addAttribute("courses", courseService.getAllCourse());
        return "enrollments/filter-by-course-page";
    }
}
