package com.books.books.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data()
public class BookRequestDTO {

    @NotBlank(message = "El titulo es obligatorio")
    String title;

    @NotBlank(message = "El autor es obligatorio")
    String author;

    @PastOrPresent(message = "La fecha no puede ser futura")
    LocalDate publicationDate;

    @NotBlank(message = "La categoría es obligatoria")
    String category;

    @NotNull(message = "El ISBN es obligatorio")
    Long isbn;

    @Min(1)
    @Max(5)
    @NotNull
    Integer valoration;

    @NotNull()
    Boolean isVisible;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    @NotNull
    Integer currentStock;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    Double price;

    String imageUrl;

    @NotBlank(message = "La descripción es obligatoria")
    String description;

    String editorial;
    String language;

    @Positive(message = "El número de páginas debe ser mayor a 0")
    Integer pages;

    @Positive(message = "El número de edición debe ser mayor a 0")
    Integer edition;

    String biography;
    String authorPhotoUrl;
}
