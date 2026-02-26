package com.books.books.service.interfaces;

import com.books.books.DTO.BookSearchResponse;
import com.books.books.domain.Book;

import java.time.LocalDate;
import java.util.List;

public interface IBookService {
    List<Book> findAll();

    Book save(Book book);

    Book findById(Long id);

    void deleteById(Long id);

    BookSearchResponse searchBooks(String q, String title, String author, LocalDate publicationDate,
                                   String category, Long isbn, Integer valoration, Boolean isVisible,
                                   Double priceMin, Double priceMax, Boolean aggregate, int page, int size);

    List<String> getSuggestions(String q, int limit);
}
