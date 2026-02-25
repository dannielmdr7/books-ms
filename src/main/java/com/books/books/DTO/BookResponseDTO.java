package com.books.books.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data()
public class BookResponseDTO {
    Long id;
    String title;
    String author;
    LocalDate publicationDate;
    String category;
    Long isbn;
    Integer valoration;
    Boolean isVisible;
    Integer currentStock;
    Double price;
    String imageUrl;
    String description;
    String editorial;
    String language;
    Integer pages;
    Integer edition;
    String biography;
    String authorPhotoUrl;
}
