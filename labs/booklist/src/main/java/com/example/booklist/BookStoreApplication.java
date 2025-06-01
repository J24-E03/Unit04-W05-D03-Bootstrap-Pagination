package com.example.booklist;

import com.example.booklist.model.Author;
import com.example.booklist.model.Book;
import com.example.booklist.model.Genre;
import com.example.booklist.model.Publisher;
import com.example.booklist.repository.AuthorRepository;
import com.example.booklist.repository.BookRepository;
import com.example.booklist.repository.GenreRepository;
import com.example.booklist.repository.PublisherRepository;
import com.example.booklist.service.AuthorService;
import com.example.booklist.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class BookStoreApplication implements CommandLineRunner {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final PublisherRepository publisherRepository;
	private final GenreRepository genreRepository;


    public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (bookRepository.count() > 20) return;

		List<Author> authors = authorRepository.findAll();
		List<Publisher> publishers = publisherRepository.findAll();
		List<Genre> genres = genreRepository.findAll();

		List<Book> newBooks = List.of(
				new Book(null, "Animal Farm", 1945, true, 8.99, getGenre(genres, "Dystopian"), List.of(getAuthor(authors, "George Orwell")), List.of(getPublisher(publishers, "Penguin Books"))),
				new Book(null, "The Two Towers", 1954, true, 13.99, getGenre(genres, "Fantasy"), List.of(getAuthor(authors, "J.R.R. Tolkien")), List.of(getPublisher(publishers, "Bloomsbury Publishing"))),
				new Book(null, "Test-Driven Development", 2003, true, 35.00, getGenre(genres, "Programming"), List.of(getAuthor(authors, "Robert C. Martin")), List.of(getPublisher(publishers, "Addison-Wesley"))),
				new Book(null, "Working Effectively with Legacy Code", 2004, true, 42.00, getGenre(genres, "Programming"), List.of(getAuthor(authors, "Robert C. Martin")), List.of(getPublisher(publishers, "O'Reilly Media"))),
				new Book(null, "Java Concurrency in Practice", 2006, true, 44.00, getGenre(genres, "Programming"), List.of(getAuthor(authors, "Joshua Bloch")), List.of(getPublisher(publishers, "Addison-Wesley"))),
				new Book(null, "Design Patterns", 1994, true, 38.99, getGenre(genres, "Programming"), List.of(getAuthor(authors, "Martin Fowler")), List.of(getPublisher(publishers, "O'Reilly Media"))),
				new Book(null, "Carrie", 1974, true, 11.50, getGenre(genres, "Horror"), List.of(getAuthor(authors, "Stephen King")), List.of(getPublisher(publishers, "Penguin Books"))),
				new Book(null, "Harry Potter and the Chamber of Secrets", 1998, true, 21.00, getGenre(genres, "Fantasy"), List.of(getAuthor(authors, "J.K. Rowling")), List.of(getPublisher(publishers, "Bloomsbury Publishing"))),
				new Book(null, "Go Set a Watchman", 2015, true, 14.00, getGenre(genres, "Classic"), List.of(getAuthor(authors, "Harper Lee")), List.of(getPublisher(publishers, "Random House"))),
				new Book(null, "Billy Budd", 1924, true, 9.99, getGenre(genres, "Classic"), List.of(getAuthor(authors, "Herman Melville")), List.of(getPublisher(publishers, "Penguin Books"))),
				new Book(null, "Code Complete", 2004, true, 46.00, getGenre(genres, "Programming"), List.of(getAuthor(authors, "Robert C. Martin")), List.of(getPublisher(publishers, "O'Reilly Media"))),
				new Book(null, "Refactoring UI", 2018, true, 30.00, getGenre(genres, "Programming"), List.of(getAuthor(authors, "Martin Fowler")), List.of(getPublisher(publishers, "Addison-Wesley"))),
				new Book(null, "The Stand", 1978, true, 19.99, getGenre(genres, "Horror"), List.of(getAuthor(authors, "Stephen King")), List.of(getPublisher(publishers, "Penguin Books"))),
				new Book(null, "Harry Potter and the Prisoner of Azkaban", 1999, true, 22.00, getGenre(genres, "Fantasy"), List.of(getAuthor(authors, "J.K. Rowling")), List.of(getPublisher(publishers, "Bloomsbury Publishing"))),
				new Book(null, "The Great Gatsby", 1925, true, 10.00, getGenre(genres, "Classic"), List.of(getAuthor(authors, "Harper Lee")), List.of(getPublisher(publishers, "Random House"))),
				new Book(null, "Moby Dick: Annotated", 1851, true, 12.00, getGenre(genres, "Classic"), List.of(getAuthor(authors, "Herman Melville")), List.of(getPublisher(publishers, "Random House")))
		);

		bookRepository.saveAll(newBooks);
	}

	private Genre getGenre(List<Genre> genres, String name) {
		return genres.stream().filter(g -> g.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	private Author getAuthor(List<Author> authors, String name) {
		return authors.stream().filter(a -> a.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	private Publisher getPublisher(List<Publisher> publishers, String name) {
		return publishers.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
}
