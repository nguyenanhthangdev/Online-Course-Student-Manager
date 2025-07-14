package com.example.onlinecoursestudentmanager.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Bắt tất cả lỗi chưa đoán trước
    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, Model model) {
        // Ghi log lỗi ra console (hoặc dùng Logger)
        ex.printStackTrace();

        // Gửi thông báo thân thiện ra giao diện
        model.addAttribute("errorMessage", "Đã xảy ra lỗi không mong muốn. Vui lòng thử lại sau.");
        return "error/error-page";  // tên file HTML trong templates/
    }
}