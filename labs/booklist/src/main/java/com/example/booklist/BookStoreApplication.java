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
	public void run(String... args) throws Exception {
//		// --- Genres ---
//		Genre fantasy = new Genre(null, "Fantasy");
//		Genre dystopian = new Genre(null, "Dystopian");
//		Genre programming = new Genre(null, "Programming");
//		Genre horror = new Genre(null, "Horror");
//		Genre classic = new Genre(null, "Classic");
//
//		genreRepository.saveAll(List.of(fantasy, dystopian, programming, horror, classic));
//
//		// --- Publishers ---
//		Publisher penguin = new Publisher(null, "Penguin Books");
//		Publisher oReilly = new Publisher(null, "O'Reilly Media");
//		Publisher addison = new Publisher(null, "Addison-Wesley");
//		Publisher bloomsbury = new Publisher(null, "Bloomsbury Publishing");
//		Publisher randomHouse = new Publisher(null, "Random House");
//
//		publisherRepository.saveAll(List.of(penguin, oReilly, addison, bloomsbury, randomHouse));
//
//		// --- Authors ---
//		Author tolkien = new Author(null, "J.R.R. Tolkien", "English writer, philologist, and academic", new ArrayList<>());
//		Author orwell = new Author(null, "George Orwell", "English novelist and essayist", new ArrayList<>());
//		Author martin = new Author(null, "Robert C. Martin", "Software engineer and author", new ArrayList<>());
//		Author hunt = new Author(null, "Andrew Hunt", "Co-author of 'The Pragmatic Programmer'", new ArrayList<>());
//		Author bloch = new Author(null, "Joshua Bloch", "Java specialist and author", new ArrayList<>());
//		Author fowler = new Author(null, "Martin Fowler", "Software engineer and author", new ArrayList<>());
//		Author rowling = new Author(null, "J.K. Rowling", "British author of Harry Potter series", new ArrayList<>());
//		Author king = new Author(null, "Stephen King", "Horror fiction author", new ArrayList<>());
//		Author lee = new Author(null, "Harper Lee", "Wrote 'To Kill a Mockingbird'", new ArrayList<>());
//		Author melville = new Author(null, "Herman Melville", "Wrote 'Moby Dick'", new ArrayList<>());
//
//		List<Author> authors = List.of(tolkien, orwell, martin, hunt, bloch, fowler, rowling, king, lee, melville);
//		authorRepository.saveAll(authors);
//
//		// --- Books ---
//		List<Book> books = new ArrayList<>();
//
//		books.add(new Book(null, "The Hobbit", 1937, true, 12.99, fantasy, List.of(tolkien), List.of(penguin)));
//		books.add(new Book(null, "1984", 1949, true, 9.99, dystopian, List.of(orwell), List.of(randomHouse)));
//		books.add(new Book(null, "Clean Code", 2008, true, 34.99, programming, List.of(martin), List.of(addison)));
//		books.add(new Book(null, "The Pragmatic Programmer", 1999, true, 39.95, programming, List.of(hunt, martin), List.of(oReilly)));
//		books.add(new Book(null, "Effective Java", 2018, true, 45.00, programming, List.of(bloch), List.of(addison)));
//		books.add(new Book(null, "Refactoring", 1999, true, 38.00, programming, List.of(fowler, martin), List.of(addison)));
//		books.add(new Book(null, "Harry Potter and the Philosopher's Stone", 1997, true, 20.00, fantasy, List.of(rowling), List.of(bloomsbury)));
//		books.add(new Book(null, "The Shining", 1977, true, 14.50, horror, List.of(king), List.of(penguin)));
//		books.add(new Book(null, "To Kill a Mockingbird", 1960, true, 10.50, classic, List.of(lee), List.of(randomHouse)));
//		books.add(new Book(null, "The Art of Software Craftsmanship", 2020, true, 29.00, programming, List.of(martin, bloch, hunt), List.of(oReilly)));
//
//		bookRepository.saveAll(books);
//
//		// --- Set books in Authors (bidirectional setup) ---
//		tolkien.setBooks(List.of(books.get(0)));
//		orwell.setBooks(List.of(books.get(1)));
//		martin.setBooks(List.of(books.get(2), books.get(3), books.get(5), books.get(9)));
//		hunt.setBooks(List.of(books.get(3), books.get(9)));
//		bloch.setBooks(List.of(books.get(4), books.get(9)));
//		fowler.setBooks(List.of(books.get(5)));
//		rowling.setBooks(List.of(books.get(6)));
//		king.setBooks(List.of(books.get(7)));
//		lee.setBooks(List.of(books.get(8)));
//		melville.setBooks(new ArrayList<>());
//
//		authorRepository.saveAll(List.of(tolkien, orwell, martin, hunt, bloch, fowler, rowling, king, lee, melville));
	}
}
