package com.example.booklist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Title cannot be blank.")
    @Size(min = 1, max = 255, message = "Title Must be between 1-255 characters.")
    private String title;

    @Positive(message = "Year cannot be a negative number.")
    @Min(value = 1500, message = "Year should be after 1500.")
    @Max(value = 2050, message = "Year should be before 2050.")
    private int year;

    private boolean inStock;

    @Positive(message = "Price should be a positive number.")
    private double price;

    @ManyToOne
    @JoinColumn(name="genre_id")
    private Genre genre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn (name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (
            name = "book_publisher",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private List<Publisher> publishers = new ArrayList<>();
}
