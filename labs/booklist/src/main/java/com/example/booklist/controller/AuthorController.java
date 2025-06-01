package com.example.booklist.controller;

import com.example.booklist.model.Author;
import com.example.booklist.service.AuthorService;
import com.example.booklist.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping
    private String authors(Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors/authors-list";
    }

    @GetMapping("/{id}")
    private String author(Model model, @PathVariable Long id) {
        model.addAttribute("author", authorService.findAuthorById(id));
        model.addAttribute("books", bookService.findAllBooks());
        return "authors/author-form";
    }

    @PostMapping("/update/{id}")
    private String updateAuthor(@ModelAttribute Author author, @RequestParam(required = false) List<Long> bookIds, Model model) {
        authorService.updateAuthor(author, bookIds);
       return "redirect:/authors";
    }

    @PostMapping("/delete/{id}")
    private String deleteAuthor(@PathVariable Long id, Model model) {
        authorService.deleteAuthorById(id);
        return "redirect:/authors";
    }

    @GetMapping("/new")
    private String newAuthor(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("books", bookService.findAllBooks());
        return "authors/author-form";
    }

    @PostMapping("/create")
    private String createAuthor(@ModelAttribute Author author, @RequestParam(required = false) List<Long> bookIds, Model model) {
        authorService.addNewAuthor(author, bookIds);
        return "redirect:/authors";
    }
}
