package com.books.books.service.interfaces;

import com.books.books.domain.Book;

import java.time.LocalDate;
import java.util.List;

public interface IBookService {
    List<Book> findAll();

    Book save(Book book);

    Book findById(Long id);

    void deleteById(Long id);

    List<Book> searchBooks(String title, String author, LocalDate publicationDate,
                           String category, Long isbn, Integer valoration, Boolean isVisible);
}
