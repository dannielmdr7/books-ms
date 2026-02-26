package com.books.books.service.implementations;

import com.books.books.DTO.BookResponseDTO;
import com.books.books.domain.Book;
import com.books.books.event.BookDeletedEvent;
import com.books.books.event.BookSavedEvent;
import com.books.books.exception.ResourceNotFoundException;
import com.books.books.mapper.BookMapper;
import com.books.books.repository.BookRepository;
import com.books.books.repository.specifications.BookSpecification;
import com.books.books.search.BookDocument;
import com.books.books.search.BookSearchDataAccess;
import com.books.books.service.interfaces.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
                null, null, null, null, null, null, null, true
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
    public List<BookResponseDTO> searchBooks(String q, String title, String author, LocalDate publicationDate,
                                              String category, Long isbn, Integer valoration, Boolean isVisible) {
        if (bookSearchDataAccess != null) {
            List<BookDocument> docs = bookSearchDataAccess.searchBooks(
                    q, title, author, publicationDate, category, isbn, valoration, isVisible
            );
            return docs.stream()
                    .map(bookMapper::toResponseDTO)
                    .collect(Collectors.toList());
        }
        Specification<Book> spec = BookSpecification.withFilters(
                q, title, author, publicationDate, category, isbn, valoration, isVisible
        );
        List<Book> books = bookRepository.findAll(spec);
        return bookMapper.toBookResponseDTOList(books);
    }


}
