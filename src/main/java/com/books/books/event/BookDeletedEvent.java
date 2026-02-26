package com.books.books.event;

import com.books.books.domain.Book;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class BookDeletedEvent extends ApplicationEvent {

    private final Book book;

    public BookDeletedEvent(Object source, Book book) {
        super(source);
        this.book = book;
    }
}
