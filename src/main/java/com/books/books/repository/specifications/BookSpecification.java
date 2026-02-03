package com.books.books.repository.specifications;


import com.books.books.domain.Book;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification {

    public static Specification<Book> withFilters(
            String title,
            String author,
            LocalDate publicationDate,
            String category,
            Long isbn,
            Integer valoration,
            Boolean isVisible) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                ));
            }

            if (author != null && !author.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("author")),
                        "%" + author.toLowerCase() + "%"
                ));
            }

            if (publicationDate != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("publicationDate"),
                        publicationDate
                ));
            }

            if (category != null && !category.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("category")),
                        "%" + category.toLowerCase() + "%"
                ));
            }

            if (isbn != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("isbn"),
                        isbn
                ));
            }

            if (valoration != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("valoration"),
                        valoration
                ));
            }

            if (isVisible != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("isVisible"),
                        isVisible
                ));
            } else {
                predicates.add(criteriaBuilder.equal(
                        root.get("isVisible"),
                        true
                ));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}