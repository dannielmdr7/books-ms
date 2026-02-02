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
}
