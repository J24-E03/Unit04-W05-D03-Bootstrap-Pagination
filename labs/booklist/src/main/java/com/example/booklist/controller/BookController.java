package com.example.booklist.controller;

import com.example.booklist.model.Book;
import com.example.booklist.service.AuthorService;
import com.example.booklist.service.BookService;
import com.example.booklist.service.GenreService;
import com.example.booklist.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;


    @GetMapping
    public String getBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Boolean inStock,
            @RequestParam(required = false) Integer startYear,
            @RequestParam(required = false) Integer endYear,
                           @RequestParam(required = false) Double price,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           Model model) {
        Page<Book> bookPage = bookService.searchBooks(title, author, publisher, inStock, startYear, endYear, genre, price, page, size);

        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage",page);
        model.addAttribute("hasPrevious",bookPage.hasPrevious());
        model.addAttribute("hasNext",bookPage.hasNext());
        model.addAttribute("size",size);

        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("publisher", publisher);
        model.addAttribute("genre", genre);
        model.addAttribute("startYear", startYear);
        model.addAttribute("endYear", endYear);
        model.addAttribute("inStock", inStock);
        model.addAttribute("price", price);
        return "books/books-list";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("index", -1);
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.findAll());
        return "books/book-form";
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute Book book, BindingResult bindingResult, @RequestParam List<Long> authorIds, @RequestParam List<Long> publisherIds, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("index", -1);
            model.addAttribute("authors", authorService.findAllAuthors());
            model.addAttribute("genres", genreService.getAllGenres());
            model.addAttribute("publishers", publisherService.findAll());
            return "books/book-form";
        }
        bookService.addNewBook(book, authorIds, publisherIds);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String vireBookDetails(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        return "books/book-details";
    }

    @GetMapping("/update/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.findAll());

        return "books/book-form";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@Valid @ModelAttribute Book book, BindingResult bindingResult, @RequestParam List<Long> authorIds, @RequestParam List<Long> publisherIds, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.findAllAuthors());
            model.addAttribute("genres", genreService.getAllGenres());
            model.addAttribute("publishers", publisherService.findAll());
            return "books/book-form";
        }
      bookService.updateBook(book,authorIds,publisherIds);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

}
