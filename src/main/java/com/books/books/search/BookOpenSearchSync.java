package com.books.books.search;

import com.books.books.domain.Book;
import com.books.books.event.BookDeletedEvent;
import com.books.books.event.BookSavedEvent;
import com.books.books.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
@org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(name = "opensearch.enabled", havingValue = "true")
public class BookOpenSearchSync {

    private final BookRepository bookRepository;
    private final BookSearchDataAccess bookSearchDataAccess;

    @PostConstruct
    public void syncAllOnStartup() {
        try {
            List<Book> allBooks = bookRepository.findAll();
            List<BookDocument> documents = allBooks.stream()
                    .map(this::toDocument)
                    .collect(Collectors.toList());
            documents.forEach(bookSearchDataAccess::save);
            log.info("OpenSearch: sincronizados {} libros en el índice", documents.size());
        } catch (Exception e) {
            log.warn("OpenSearch: no se pudo sincronizar el índice en el arranque: {}", e.getMessage());
        }
    }

    @TransactionalEventListener
    public void onBookSaved(BookSavedEvent event) {
        try {
            Book book = event.getBook();
            BookDocument doc = toDocument(book);
            bookSearchDataAccess.save(doc);
            log.debug("OpenSearch: índice actualizado para libro id={}", book.getId());
        } catch (Exception e) {
            log.warn("OpenSearch: error al indexar libro id={}: {}", event.getBook().getId(), e.getMessage());
        }
    }

    @TransactionalEventListener
    public void onBookDeleted(BookDeletedEvent event) {
        try {
            Book book = event.getBook();
            bookSearchDataAccess.deleteById(String.valueOf(book.getId()));
            log.debug("OpenSearch: libro id={} eliminado del índice", book.getId());
        } catch (Exception e) {
            log.warn("OpenSearch: error al eliminar libro id={} del índice: {}", event.getBook().getId(), e.getMessage());
        }
    }

    private BookDocument toDocument(Book book) {
        return BookDocument.builder()
                .id(String.valueOf(book.getId()))
                .bookId(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationDate(book.getPublicationDate())
                .category(book.getCategory())
                .isbn(book.getIsbn())
                .valoration(book.getValoration())
                .isVisible(book.getIsVisible())
                .currentStock(book.getCurrentStock())
                .price(book.getPrice())
                .imageUrl(book.getImageUrl())
                .description(book.getDescription())
                .editorial(book.getEditorial())
                .language(book.getLanguage())
                .pages(book.getPages())
                .edition(book.getEdition())
                .biography(book.getBiography())
                .authorPhotoUrl(book.getAuthorPhotoUrl())
                .build();
    }
}
