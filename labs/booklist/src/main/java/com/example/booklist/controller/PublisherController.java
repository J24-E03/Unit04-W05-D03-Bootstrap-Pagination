package com.example.booklist.controller;

import com.example.booklist.model.Author;
import com.example.booklist.model.Publisher;
import com.example.booklist.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public String showAllPublishers(Model model) {
        model.addAttribute("publishers", publisherService.findAll());
        return "publishers/publishers-list";
    }

    @GetMapping("/new")
    public String newPublisher(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "/publishers/publisher-form";
    }

    @PostMapping("/create")
    public String createPublisher(@Valid @ModelAttribute("publisher") Publisher publisher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("publisher", publisher);
            return "/publishers/publisher-form";
        }
        publisherService.save(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/{id}")
    private String publisher(Model model, @PathVariable Long id) {
        model.addAttribute("publisher", publisherService.findAuthorById(id));
        return "publishers/publisher-form";
    }

    @PostMapping("/update/{id}")
    private String updatePublisher(@ModelAttribute Publisher publisher,Model model) {
        publisherService.updatePublisher(publisher);
        return "redirect:/publishers";
    }
}
