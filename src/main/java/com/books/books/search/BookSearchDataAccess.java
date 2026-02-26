package com.books.books.search;

import com.books.books.DTO.AggregationDetails;
import com.books.books.DTO.BookSearchResponse;
import com.books.books.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.common.unit.Fuzziness;
import org.opensearch.index.query.BoolQueryBuilder;
import org.opensearch.index.query.MultiMatchQueryBuilder;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.data.client.orhlc.NativeSearchQueryBuilder;
import org.opensearch.data.client.orhlc.OpenSearchAggregations;
import org.opensearch.search.aggregations.AggregationBuilders;
import org.opensearch.search.aggregations.bucket.range.ParsedRange;
import org.opensearch.search.aggregations.bucket.range.Range;
import org.opensearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "opensearch.enabled", havingValue = "true")
public class BookSearchDataAccess {

    private final BookSearchRepository bookSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;
    private final BookMapper bookMapper;

    private static final String[] SEARCH_FIELDS = {"title", "author", "description", "biography", "category"};

    private static final String AGG_CATEGORY = "category";
    private static final String AGG_AUTHOR = "author";
    private static final String AGG_PRICE_RANGE = "price_range";

    public void save(BookDocument document) {
        bookSearchRepository.save(document);
    }

    public void saveAll(Iterable<BookDocument> documents) {
        bookSearchRepository.saveAll(documents);
    }

    public void deleteById(String id) {
        bookSearchRepository.deleteById(id);
    }

    public List<String> getSuggestions(String q, int limit) {
        if (!StringUtils.hasText(q) || q.length() < 2) {
            return List.of();
        }
        BoolQueryBuilder querySpec = QueryBuilders.boolQuery()
                .must(QueryBuilders.multiMatchQuery(q, "title", "author")
                        .type(MultiMatchQueryBuilder.Type.BEST_FIELDS)
                        .fuzziness(Fuzziness.AUTO)
                        .prefixLength(1))
                .must(QueryBuilders.termQuery("isVisible", true));

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder()
                .withQuery(querySpec)
                .withPageable(PageRequest.of(0, limit * 2));

        Query query = builder.build();
        SearchHits<BookDocument> result = elasticsearchOperations.search(query, BookDocument.class);

        Set<String> suggestions = new LinkedHashSet<>();
        for (SearchHit<BookDocument> hit : result.getSearchHits()) {
            BookDocument doc = hit.getContent();
            if (doc.getTitle() != null && !doc.getTitle().isBlank()) {
                suggestions.add(doc.getTitle());
            }
            if (doc.getAuthor() != null && !doc.getAuthor().isBlank()) {
                suggestions.add(doc.getAuthor());
            }
            if (suggestions.size() >= limit) break;
        }
        return new ArrayList<>(suggestions).stream().limit(limit).toList();
    }

    public BookSearchResponse searchBooks(String q, String title, String author, LocalDate publicationDate,
                                          String category, Long isbn, Integer valoration, Boolean isVisible,
                                          Double priceMin, Double priceMax, Boolean aggregate, int page, int size) {

        BoolQueryBuilder querySpec = QueryBuilders.boolQuery();

        if (StringUtils.hasText(q)) {
            querySpec.must(QueryBuilders.multiMatchQuery(q, SEARCH_FIELDS)
                    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS)
                    .fuzziness(Fuzziness.AUTO)
                    .prefixLength(1)
                    .maxExpansions(50));
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
            querySpec.must(QueryBuilders.termQuery("category.keyword", category));
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

        if (priceMin != null || priceMax != null) {
            var rangeQuery = QueryBuilders.rangeQuery("price");
            if (priceMin != null) rangeQuery.gte(priceMin);
            if (priceMax != null) rangeQuery.lte(priceMax);
            querySpec.must(rangeQuery);
        }

        if (!querySpec.hasClauses()) {
            querySpec.must(QueryBuilders.matchAllQuery());
        }

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().withQuery(querySpec);

        if (Boolean.TRUE.equals(aggregate)) {
            builder.withAggregations(
                    AggregationBuilders.terms(AGG_CATEGORY).field("category.keyword").size(100),
                    AggregationBuilders.terms(AGG_AUTHOR).field("author.keyword").size(50),
                    AggregationBuilders.range(AGG_PRICE_RANGE).field("price")
                            .addRange(0, 20.01).addRange(20.01, 50).addRange(50, 100).addRange(100, 500)
            );
        }

        builder.withPageable(PageRequest.of(page, size));

        Query query = builder.build();
        SearchHits<BookDocument> result = elasticsearchOperations.search(query, BookDocument.class);

        List<BookDocument> books = result.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        Map<String, List<AggregationDetails>> aggregations = new HashMap<>();
        if (Boolean.TRUE.equals(aggregate) && result.hasAggregations() && result.getAggregations() != null) {
            aggregations = parseAggregations(result, q, title, author, publicationDate, category, isbn, valoration, isVisible, priceMin, priceMax);
        }

        long totalElements = result.getTotalHits();
        int totalPages = size > 0 ? (int) Math.ceil((double) totalElements / size) : 0;

        return new BookSearchResponse(
                books.stream().map(bookMapper::toResponseDTO).collect(Collectors.toList()),
                aggregations,
                totalElements,
                totalPages
        );
    }

    private Map<String, List<AggregationDetails>> parseAggregations(SearchHits<?> searchHits,
                                                                     String q, String title, String author,
                                                                     LocalDate publicationDate, String category,
                                                                     Long isbn, Integer valoration, Boolean isVisible,
                                                                     Double priceMin, Double priceMax) {
        Map<String, List<AggregationDetails>> result = new HashMap<>();
        var aggs = searchHits.getAggregations();
        if (aggs == null) return result;

        var openSearchAggs = (OpenSearchAggregations) aggs;
        var aggsMap = openSearchAggs.aggregations().asMap();
        var enc = java.nio.charset.StandardCharsets.UTF_8;

        var categoryAgg = aggsMap.get(AGG_CATEGORY);
        if (categoryAgg instanceof ParsedStringTerms categoryTerms) {
            result.put(AGG_CATEGORY, categoryTerms.getBuckets().stream()
                    .filter(b -> b.getDocCount() > 0)
                    .map(b -> new AggregationDetails(
                            b.getKeyAsString(),
                            b.getDocCount(),
                            buildUri("category", java.net.URLEncoder.encode(b.getKeyAsString(), enc), q, title, author, publicationDate, null, isbn, valoration, isVisible, priceMin, priceMax)))
                    .collect(Collectors.toList()));
        }

        var authorAgg = aggsMap.get(AGG_AUTHOR);
        if (authorAgg instanceof ParsedStringTerms authorTerms) {
            result.put(AGG_AUTHOR, authorTerms.getBuckets().stream()
                    .filter(b -> b.getDocCount() > 0)
                    .map(b -> new AggregationDetails(
                            b.getKeyAsString(),
                            b.getDocCount(),
                            buildUri("author", java.net.URLEncoder.encode(b.getKeyAsString(), enc), q, title, author, publicationDate, category, isbn, valoration, isVisible, priceMin, priceMax)))
                    .collect(Collectors.toList()));
        }

        var priceAgg = aggsMap.get(AGG_PRICE_RANGE);
        if (priceAgg instanceof ParsedRange priceRange) {
            List<AggregationDetails> priceBuckets = new ArrayList<>();
            String[] labels = {"Hasta $20", "$20-$50", "$50-$100", "$100-$500"};
            int i = 0;
            for (Range.Bucket bucket : priceRange.getBuckets()) {
                if (bucket.getDocCount() == 0) {
                    i++;
                    continue;
                }
                String label = i < labels.length ? labels[i] : bucket.getKeyAsString();
                String bucketPriceMin = bucket.getFrom() != null ? String.valueOf(((Number) bucket.getFrom()).doubleValue()) : "0";
                String bucketPriceMax = bucket.getTo() != null ? String.valueOf(((Number) bucket.getTo()).doubleValue()) : "500";
                priceBuckets.add(new AggregationDetails(
                        label,
                        bucket.getDocCount(),
                        buildPriceUri(bucketPriceMin, bucketPriceMax, q, title, author, publicationDate, category, isbn, valoration, isVisible)));
                i++;
            }
            result.put(AGG_PRICE_RANGE, priceBuckets);
        }

        return result;
    }

    private String buildUri(String newKey, String newValue, String q, String title, String author,
                           LocalDate publicationDate, String category, Long isbn, Integer valoration, Boolean isVisible,
                           Double priceMin, Double priceMax) {
        List<String> params = new ArrayList<>();
        params.add(newKey + "=" + newValue);
        if (StringUtils.hasText(q)) params.add("q=" + java.net.URLEncoder.encode(q, java.nio.charset.StandardCharsets.UTF_8));
        if (StringUtils.hasText(title)) params.add("title=" + java.net.URLEncoder.encode(title, java.nio.charset.StandardCharsets.UTF_8));
        if (StringUtils.hasText(author)) params.add("author=" + java.net.URLEncoder.encode(author, java.nio.charset.StandardCharsets.UTF_8));
        if (publicationDate != null) params.add("publicationDate=" + publicationDate);
        if (StringUtils.hasText(category)) params.add("category=" + java.net.URLEncoder.encode(category, java.nio.charset.StandardCharsets.UTF_8));
        if (isbn != null) params.add("isbn=" + isbn);
        if (valoration != null) params.add("valoration=" + valoration);
        if (isVisible != null) params.add("isVisible=" + isVisible);
        if (priceMin != null) params.add("priceMin=" + priceMin);
        if (priceMax != null) params.add("priceMax=" + priceMax);
        return "?" + String.join("&", params);
    }

    private String buildPriceUri(String priceMin, String priceMax, String q, String title, String author,
                                 LocalDate publicationDate, String category, Long isbn, Integer valoration, Boolean isVisible) {
        List<String> params = new ArrayList<>();
        params.add("priceMin=" + priceMin);
        params.add("priceMax=" + priceMax);
        if (StringUtils.hasText(q)) params.add("q=" + java.net.URLEncoder.encode(q, java.nio.charset.StandardCharsets.UTF_8));
        if (StringUtils.hasText(title)) params.add("title=" + java.net.URLEncoder.encode(title, java.nio.charset.StandardCharsets.UTF_8));
        if (StringUtils.hasText(author)) params.add("author=" + java.net.URLEncoder.encode(author, java.nio.charset.StandardCharsets.UTF_8));
        if (publicationDate != null) params.add("publicationDate=" + publicationDate);
        if (StringUtils.hasText(category)) params.add("category=" + java.net.URLEncoder.encode(category, java.nio.charset.StandardCharsets.UTF_8));
        if (isbn != null) params.add("isbn=" + isbn);
        if (valoration != null) params.add("valoration=" + valoration);
        if (isVisible != null) params.add("isVisible=" + isVisible);
        return "?" + String.join("&", params);
    }
}
