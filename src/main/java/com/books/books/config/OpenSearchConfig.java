package com.books.books.config;

import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration;
import org.opensearch.data.client.orhlc.ClientConfiguration;
import org.opensearch.data.client.orhlc.RestClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ConditionalOnProperty(name = "opensearch.enabled", havingValue = "true")
@EnableElasticsearchRepositories(basePackages = "com.books.books.search")
public class OpenSearchConfig extends AbstractOpenSearchConfiguration {

    @Value("${opensearch.host}")
    private String host;

    @Value("${opensearch.port:443}")
    private int port;

    @Value("${opensearch.ssl:true}")
    private boolean useSsl;

    @Value("${opensearch.credentials.user:}")
    private String username;

    @Value("${opensearch.credentials.password:}")
    private String password;

    @Override
    public RestHighLevelClient opensearchClient() {
        ClientConfiguration clientConfiguration = buildClientConfiguration();
        return RestClients.create(clientConfiguration).rest();
    }

    private ClientConfiguration buildClientConfiguration() {
        String connection = host + ":" + port;
        boolean useAuth = username != null && !username.isBlank();

        if (useSsl && useAuth) {
            return ClientConfiguration.builder()
                    .connectedTo(connection)
                    .usingSsl()
                    .withBasicAuth(username, password != null ? password : "")
                    .build();
        }
        if (useSsl) {
            return ClientConfiguration.builder()
                    .connectedTo(connection)
                    .usingSsl()
                    .build();
        }
        if (useAuth) {
            return ClientConfiguration.builder()
                    .connectedTo(connection)
                    .withBasicAuth(username, password != null ? password : "")
                    .build();
        }
        return ClientConfiguration.builder()
                .connectedTo(connection)
                .build();
    }
}
