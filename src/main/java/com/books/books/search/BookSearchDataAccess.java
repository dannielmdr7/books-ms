package com.books.books.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.index.query.BoolQueryBuilder;
import org.opensearch.index.query.MultiMatchQueryBuilder;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.data.client.orhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "opensearch.enabled", havingValue = "true")
public class BookSearchDataAccess {

    private final BookSearchRepository bookSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    private static final String[] SEARCH_FIELDS = {"title", "author", "description", "biography", "category"};

    public void save(BookDocument document) {
        bookSearchRepository.save(document);
    }

    public void deleteById(String id) {
        bookSearchRepository.deleteById(id);
    }

    public List<BookDocument> searchBooks(String q, String title, String author, LocalDate publicationDate,
                                          String category, Long isbn, Integer valoration, Boolean isVisible) {

        BoolQueryBuilder querySpec = QueryBuilders.boolQuery();

        if (StringUtils.hasText(q)) {
            querySpec.must(QueryBuilders.multiMatchQuery(q, SEARCH_FIELDS)
                    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS));
        }

        if (StringUtils.hasText(title)) {
            querySpec.must(QueryBuilders.matchQuery("title", title));
        }

        if (StringUtils.hasText(author)) {
            querySpec.must(QueryBuilders.matchQuery("author", author));
        }

        if (publicationDate != null) {
            querySpec.must(QueryBuilders.termQuery("publicationDate", publicationDate.toString()));
        }

        if (StringUtils.hasText(category)) {
            querySpec.must(QueryBuilders.matchQuery("category", category));
        }

        if (isbn != null) {
            querySpec.must(QueryBuilders.termQuery("isbn", isbn));
        }

        if (valoration != null) {
            querySpec.must(QueryBuilders.termQuery("valoration", valoration));
        }

        if (isVisible != null) {
            querySpec.must(QueryBuilders.termQuery("isVisible", isVisible));
        } else {
            querySpec.must(QueryBuilders.termQuery("isVisible", true));
        }

        if (!querySpec.hasClauses()) {
            querySpec.must(QueryBuilders.matchAllQuery());
        }

        Query query = new NativeSearchQueryBuilder().withQuery(querySpec).build();
        SearchHits<BookDocument> result = elasticsearchOperations.search(query, BookDocument.class);

        return result.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
