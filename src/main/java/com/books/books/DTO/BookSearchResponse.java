package com.books.books.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookSearchResponse {

    private List<BookResponseDTO> books;
    private Map<String, List<AggregationDetails>> aggregations;
}
