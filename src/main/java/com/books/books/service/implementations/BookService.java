package com.books.books.service.implementations;

import com.books.books.domain.Book;
import com.books.books.exception.ResourceNotFoundException;
import com.books.books.repository.BookRepository;
import com.books.books.service.interfaces.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service()
@RequiredArgsConstructor()
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    /*public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }*/

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
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


}
