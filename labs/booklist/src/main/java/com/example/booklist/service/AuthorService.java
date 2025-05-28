package com.example.booklist.service;

import com.example.booklist.exception.ResourceNotFound;
import com.example.booklist.model.Author;
import com.example.booklist.model.Book;
import com.example.booklist.repository.AuthorRepository;
import com.example.booklist.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public void updateAuthor(Author author, List<Long> bookIds) {

        if (bookIds != null && !bookIds.isEmpty()) {
            List<Book> books = authorRepository.findById(author.getId()).get().getBooks();
            books.forEach(book -> book.getAuthors().remove(author));
            author.getBooks().clear();
            List<Book> newBooks = bookRepository.findAllById(bookIds);
            newBooks.forEach(book -> {
                author.getBooks().add(book);
                book.getAuthors().add(author);
            });
            bookRepository.saveAll(books);
            author.setBooks(books);
            authorRepository.save(author);
        }
    }
    public void addNewAuthor(Author author, List<Long> bookIds) {
        saveAuthor(author);
        if (bookIds != null && !bookIds.isEmpty()) {
            author.getBooks().clear();
            List<Book> books = bookRepository.findAllById(bookIds);
            books.forEach(book -> {
                author.getBooks().add(book);
                book.getAuthors().add(author);
            });
            bookRepository.saveAll(books);
            author.setBooks(books);
            authorRepository.save(author);
        }
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Author not found"));
    }

    public void deleteAuthorById(Long id) {
        Author author = findAuthorById(id);

        List<Book> books = author.getBooks();
        for (Book book : books) {
            List<Author> authors = book.getAuthors();
            authors.remove(author);
            book.setAuthors(authors);
        }
        bookRepository.saveAll(books);
        authorRepository.deleteById(id);
    }

    public List<Author> findAllAuthorsByBookId(List<Long> authorIds) {
        return authorRepository.findAllById(authorIds);
    }

    public void saveAllAuthors(List<Author> authors) {
        authorRepository.saveAll(authors);
    }
}
