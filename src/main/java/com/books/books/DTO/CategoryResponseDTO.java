package com.books.books.DTO;

import lombok.Data;

@Data
public class CategoryResponseDTO {
    Long id;
    String name;
    String description;
    String slug;
}
