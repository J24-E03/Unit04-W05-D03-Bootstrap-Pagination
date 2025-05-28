package com.example.booklist.service;

import com.example.booklist.exception.ResourceNotFound;
import com.example.booklist.model.Author;
import com.example.booklist.model.Book;
import com.example.booklist.model.Publisher;
import com.example.booklist.repository.BookRepository;
import com.example.booklist.repository.PublisherRepository;
import com.example.booklist.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final PublisherRepository publisherRepository;

    public List<Book> findAllBooksByIds(List<Long> ids) {
        return bookRepository.findAllById(ids);
    }

    public void saveAllBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteAuthorFromBooks(Author author) {
        List<Book> books = author.getBooks();
        for (Book book : books) {
            List<Author> authors = book.getAuthors();
            authors.remove(author);
            book.setAuthors(authors);
        }
        bookRepository.saveAll(books);
    }

    public void updateBook(Book book, List<Long> authorIds, List<Long> publisherIds) {
        List<Author> authors = bookRepository.findById(book.getId()).get().getAuthors();
        authors.forEach(author -> {
            author.getBooks().remove(book);
        });
        book.getAuthors().clear();
        List<Author> newAuthors = authorService.findAllAuthorsByBookId(authorIds);
        newAuthors.forEach(author -> {
            author.getBooks().add(book);
            book.getAuthors().add(author);
        });
        authorService.saveAllAuthors(newAuthors);


        List<Publisher> publishers = bookRepository.findById(book.getId()).get().getPublishers();
        publishers.forEach(publicher -> {
            publicher.getBooks().remove(book);
        });
        book.getPublishers().clear();
        List<Publisher> newPublishers = publisherRepository.findAllById(publisherIds);
        newPublishers.forEach(publisher -> {
            publisher.getBooks().add(book);
            book.getPublishers().add(publisher);
        });
        publisherRepository.saveAll(newPublishers);

        bookRepository.save(book);
    }

    public void addNewBook(Book book, List<Long> authorIds, List<Long> publisherIds) {
        bookRepository.save(book);
        List<Author> authors = authorService.findAllAuthorsByBookId(authorIds);
        authors.forEach(author -> {
            author.getBooks().add(book);
            book.getAuthors().add(author);
        });
        authorService.saveAllAuthors(authors);
        book.setAuthors(authors);

        List<Publisher> publishers = publisherRepository.findAllById(publisherIds);
        publishers.forEach(publisher -> {
            publisher.getBooks().add(book);
            book.getPublishers().add(publisher);
        });
        publisherRepository.saveAll(publishers);
        book.setAuthors(authors);
        book.setPublishers(publishers);
        bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Book not found"));
    }

    public List<Book> searchBooks(String title, String author, String publisher,
                                  Boolean inStock, Integer startYear,
                                  Integer endYear, String genre, Double price) {

        Specification<Book> specification = Specification.where(BookSpecification.byTitleContains(title)
                .and(BookSpecification.byAuthor(author).and(BookSpecification.byPublisherNameContains(publisher))
                        .and(BookSpecification.byGenre(genre).and(BookSpecification.byInStock(inStock))
                                .and(BookSpecification.byYearBetween(startYear, endYear)).and(BookSpecification.byPriceLessThan(price)))));

        return bookRepository.findAll(specification);



    }

}
