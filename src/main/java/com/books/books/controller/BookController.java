package com.books.books.controller;

import com.books.books.DTO.BookRequestDTO;
import com.books.books.DTO.BookResponseDTO;
import com.books.books.domain.Book;
import com.books.books.mapper.BookMapper;
import com.books.books.service.interfaces.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;
    private final BookMapper bookMapper;

    @GetMapping()
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookService.findAll();
        return bookMapper.toBookResponseDTOList(books);
    }

    @PostMapping()
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        Book bookToSave = bookMapper.toEntity(bookRequestDTO);
        Book bookSaved = bookService.save(bookToSave);
        BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(bookSaved);
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(book);
        return ResponseEntity.ok(bookResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBookById(@PathVariable Long id, @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        Book bookToUpdate = bookService.findById(id);
        bookMapper.updateBookFromDTO(bookRequestDTO, bookToUpdate);
        Book updatedBook = bookService.save(bookToUpdate);
        return ResponseEntity.ok(bookMapper.toResponseDTO(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<BookResponseDTO> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publicationDate,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long isbn,
            @RequestParam(required = false) Integer valoration,
            @RequestParam(required = false) Boolean isVisible) {

        List<Book> books = bookService.searchBooks(
                title, author, publicationDate, category, isbn, valoration, isVisible
        );
        return bookMapper.toBookResponseDTOList(books);
    }

}
