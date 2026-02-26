package com.books.books.event;

import com.books.books.domain.Book;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class BookSavedEvent extends ApplicationEvent {

    private final Book book;

    public BookSavedEvent(Object source, Book book) {
        super(source);
        this.book = book;
    }
}
