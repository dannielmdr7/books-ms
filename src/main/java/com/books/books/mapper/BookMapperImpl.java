package com.books.books.mapper;

import com.books.books.DTO.BookRequestDTO;
import com.books.books.DTO.BookResponseDTO;
import com.books.books.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book toEntity(BookRequestDTO bookRequestDTO) {
        if (bookRequestDTO == null) {
            return null;
        }

        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setPublicationDate(bookRequestDTO.getPublicationDate());
        book.setCategory(bookRequestDTO.getCategory());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setValoration(bookRequestDTO.getValoration());
        book.setIsVisible(bookRequestDTO.getIsVisible());
        book.setCurrentStock(bookRequestDTO.getCurrentStock());

        return book;
    }

    @Override
    public BookResponseDTO toResponseDTO(Book book) {
        if (book == null) {
            return null;
        }

        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(book.getId());
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setAuthor(book.getAuthor());
        bookResponseDTO.setPublicationDate(book.getPublicationDate());
        bookResponseDTO.setCategory(book.getCategory());
        bookResponseDTO.setIsbn(book.getIsbn());
        bookResponseDTO.setValoration(book.getValoration());
        bookResponseDTO.setIsVisible(book.getIsVisible());
        bookResponseDTO.setCurrentStock(book.getCurrentStock());

        return bookResponseDTO;
    }

    @Override
    public List<BookResponseDTO> toBookResponseDTOList(List<Book> books) {
        if (books == null) {
            return null;
        }

        return books.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateBookFromDTO(BookRequestDTO bookRequestDTO, Book book) {
        if (bookRequestDTO == null || book == null) {
            return;
        }

        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setPublicationDate(bookRequestDTO.getPublicationDate());
        book.setCategory(bookRequestDTO.getCategory());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setValoration(bookRequestDTO.getValoration());
        book.setIsVisible(bookRequestDTO.getIsVisible());
        book.setCurrentStock(bookRequestDTO.getCurrentStock());
    }
}

