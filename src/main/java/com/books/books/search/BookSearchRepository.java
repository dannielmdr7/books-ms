package com.books.books.search;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "opensearch.enabled", havingValue = "true")
public interface BookSearchRepository extends ElasticsearchRepository<BookDocument, String> {
}
