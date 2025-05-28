package com.example.booklist.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public String handleResourceNotFound(ResourceNotFound exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "errors/error";
    }
}
