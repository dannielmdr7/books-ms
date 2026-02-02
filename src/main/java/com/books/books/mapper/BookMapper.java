package com.books.books.mapper;

import com.books.books.DTO.BookRequestDTO;
import com.books.books.DTO.BookResponseDTO;
import com.books.books.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookRequestDTO bookRequestDTO);

    BookResponseDTO toResponseDTO(Book book);

    List<BookResponseDTO> toBookResponseDTOList(List<Book> books);

    void updateBookFromDTO(BookRequestDTO bookRequestDTO, @MappingTarget Book book);
}