package com.books.books.service.implementations;

import com.books.books.domain.Book;
import com.books.books.exception.ResourceNotFoundException;
import com.books.books.repository.BookRepository;
import com.books.books.repository.specifications.BookSpecification;
import com.books.books.service.interfaces.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service()
@RequiredArgsConstructor()
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        Specification<Book> spec = BookSpecification.withFilters(
                null, null, null, null, null, null, true
        );
        return bookRepository.findAll(spec);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Libro con id: " + id + " no encontrado"));
    }


    @Override
    public void deleteById(Long id) {
        Book bookToDelete = this.findById(id);
        bookRepository.delete(bookToDelete);
    }

    @Override
    public List<Book> searchBooks(String title, String author, LocalDate publicationDate,
                                  String category, Long isbn, Integer valoration, Boolean isVisible) {
        Specification<Book> spec = BookSpecification.withFilters(
                title, author, publicationDate, category, isbn, valoration, isVisible
        );
        return bookRepository.findAll(spec);
    }


}
