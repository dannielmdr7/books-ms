package com.books.books.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "books", createIndex = true)
public class BookDocument {

    @Id
    private String id;

    @Field(type = FieldType.Long, name = "bookId")
    private Long bookId;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "author")
    private String author;

    @Field(type = FieldType.Date, name = "publicationDate", format = DateFormat.date)
    private LocalDate publicationDate;

    @Field(type = FieldType.Text, name = "category")
    private String category;

    @Field(type = FieldType.Long, name = "isbn")
    private Long isbn;

    @Field(type = FieldType.Integer, name = "valoration")
    private Integer valoration;

    @Field(type = FieldType.Boolean, name = "isVisible")
    private Boolean isVisible;

    @Field(type = FieldType.Integer, name = "currentStock")
    private Integer currentStock;

    @Field(type = FieldType.Double, name = "price")
    private Double price;

    @Field(type = FieldType.Keyword, name = "imageUrl")
    private String imageUrl;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Text, name = "editorial")
    private String editorial;

    @Field(type = FieldType.Keyword, name = "language")
    private String language;

    @Field(type = FieldType.Integer, name = "pages")
    private Integer pages;

    @Field(type = FieldType.Integer, name = "edition")
    private Integer edition;

    @Field(type = FieldType.Text, name = "biography")
    private String biography;

    @Field(type = FieldType.Keyword, name = "authorPhotoUrl")
    private String authorPhotoUrl;
}
