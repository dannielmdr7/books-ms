package com.books.books.domain;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data()
@Entity()
@Table(name = "books")
public class Book {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String author;
    LocalDate publicationDate;
    String category;
    Long isbn;
    Integer valoration;
    Boolean isVisible;
    Integer currentStock;
}
