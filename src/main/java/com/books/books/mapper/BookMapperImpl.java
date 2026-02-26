package com.books.books.mapper;

import com.books.books.DTO.BookRequestDTO;
import com.books.books.DTO.BookResponseDTO;
import com.books.books.domain.Book;
import com.books.books.search.BookDocument;
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
        book.setPrice(bookRequestDTO.getPrice());
        book.setImageUrl(bookRequestDTO.getImageUrl());
        book.setDescription(bookRequestDTO.getDescription());
        book.setEditorial(bookRequestDTO.getEditorial());
        book.setLanguage(bookRequestDTO.getLanguage());
        book.setPages(bookRequestDTO.getPages());
        book.setEdition(bookRequestDTO.getEdition());
        book.setBiography(bookRequestDTO.getBiography());
        book.setAuthorPhotoUrl(bookRequestDTO.getAuthorPhotoUrl());

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
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setImageUrl(book.getImageUrl());
        bookResponseDTO.setDescription(book.getDescription());
        bookResponseDTO.setEditorial(book.getEditorial());
        bookResponseDTO.setLanguage(book.getLanguage());
        bookResponseDTO.setPages(book.getPages());
        bookResponseDTO.setEdition(book.getEdition());
        bookResponseDTO.setBiography(book.getBiography());
        bookResponseDTO.setAuthorPhotoUrl(book.getAuthorPhotoUrl());

        return bookResponseDTO;
    }

    @Override
    public BookResponseDTO toResponseDTO(BookDocument document) {
        if (document == null) {
            return null;
        }
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(document.getBookId());
        dto.setTitle(document.getTitle());
        dto.setAuthor(document.getAuthor());
        dto.setPublicationDate(document.getPublicationDate());
        dto.setCategory(document.getCategory());
        dto.setIsbn(document.getIsbn());
        dto.setValoration(document.getValoration());
        dto.setIsVisible(document.getIsVisible());
        dto.setCurrentStock(document.getCurrentStock());
        dto.setPrice(document.getPrice());
        dto.setImageUrl(document.getImageUrl());
        dto.setDescription(document.getDescription());
        dto.setEditorial(document.getEditorial());
        dto.setLanguage(document.getLanguage());
        dto.setPages(document.getPages());
        dto.setEdition(document.getEdition());
        dto.setBiography(document.getBiography());
        dto.setAuthorPhotoUrl(document.getAuthorPhotoUrl());
        return dto;
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
        book.setPrice(bookRequestDTO.getPrice());
        book.setImageUrl(bookRequestDTO.getImageUrl());
        book.setDescription(bookRequestDTO.getDescription());
        book.setEditorial(bookRequestDTO.getEditorial());
        book.setLanguage(bookRequestDTO.getLanguage());
        book.setPages(bookRequestDTO.getPages());
        book.setEdition(bookRequestDTO.getEdition());
        book.setBiography(bookRequestDTO.getBiography());
        book.setAuthorPhotoUrl(bookRequestDTO.getAuthorPhotoUrl());
    }
}

