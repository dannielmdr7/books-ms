package com.books.books.service.implementations;

import com.books.books.DTO.BookSearchResponse;
import com.books.books.domain.Book;
import com.books.books.event.BookDeletedEvent;
import com.books.books.event.BookSavedEvent;
import com.books.books.exception.ResourceNotFoundException;
import com.books.books.mapper.BookMapper;
import com.books.books.repository.BookRepository;
import com.books.books.repository.specifications.BookSpecification;
import com.books.books.search.BookSearchDataAccess;
import com.books.books.service.interfaces.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service()
@RequiredArgsConstructor()
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired(required = false)
    private BookSearchDataAccess bookSearchDataAccess;

    @Override
    public List<Book> findAll() {
        Specification<Book> spec = BookSpecification.withFilters(
                null, null, null, null, null, null, null, true, null, null
        );
        return bookRepository.findAll(spec);
    }

    @Override
    public Book save(Book book) {
        Book saved = bookRepository.save(book);
        eventPublisher.publishEvent(new BookSavedEvent(this, saved));
        return saved;
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Libro con id: " + id + " no encontrado"));
    }


    @Override
    public void deleteById(Long id) {
        Book bookToDelete = this.findById(id);
        eventPublisher.publishEvent(new BookDeletedEvent(this, bookToDelete));
        bookRepository.delete(bookToDelete);
    }

    @Override
    public BookSearchResponse searchBooks(String q, String title, String author, LocalDate publicationDate,
                                          String category, Long isbn, Integer valoration, Boolean isVisible,
                                          Double priceMin, Double priceMax, Boolean aggregate) {
        if (bookSearchDataAccess != null) {
            return bookSearchDataAccess.searchBooks(
                    q, title, author, publicationDate, category, isbn, valoration, isVisible,
                    priceMin, priceMax, aggregate != null ? aggregate : true
            );
        }
        Specification<Book> spec = BookSpecification.withFilters(
                q, title, author, publicationDate, category, isbn, valoration, isVisible, priceMin, priceMax
        );
        List<Book> books = bookRepository.findAll(spec);
        return new BookSearchResponse(
                bookMapper.toBookResponseDTOList(books),
                new HashMap<>()
        );
    }

    @Override
    public List<String> getSuggestions(String q, int limit) {
        if (bookSearchDataAccess != null) {
            return bookSearchDataAccess.getSuggestions(q != null ? q.trim() : "", limit);
        }
        return Collections.emptyList();
    }
}
