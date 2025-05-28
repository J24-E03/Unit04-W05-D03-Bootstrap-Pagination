package com.example.booklist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Publisher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Publisher name cannot be empty.")
    @Size(min = 2, max = 255, message = "Publisher name should be between 2-255 characters.")
    private String name;

    @ManyToMany(mappedBy = "publishers", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();
}
