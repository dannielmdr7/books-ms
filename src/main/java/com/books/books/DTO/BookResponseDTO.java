package com.books.books.DTO;

import jakarta.persistence.Column;
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
}
