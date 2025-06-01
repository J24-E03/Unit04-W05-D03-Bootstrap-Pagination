package com.example.booklist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString (exclude = {"books"})
@EqualsAndHashCode (of = {"id"})
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Author name cannot be blank.")
    @Size(min = 2, max = 100, message = "Author's name should be between 2-100 characters.")
    private String name;

    @NotBlank(message = "Author's Bio cannot be blank.")
    @Size(min = 5, max = 255, message = "Author's Bio should be between 5-255 characters.")
    private String bio;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();



}
