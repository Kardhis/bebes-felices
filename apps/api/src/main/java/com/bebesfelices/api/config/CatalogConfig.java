package com.bebesfelices.api.config;

import com.bebesfelices.api.catalog.AmazonEnrichedProductCatalog;
import com.bebesfelices.api.catalog.ManualProductCatalog;
import com.bebesfelices.api.catalog.ProductCatalog;
import com.bebesfelices.api.catalog.amazon.AmazonCatalogClient;
import com.bebesfelices.api.catalog.amazon.AmazonCreatorsProperties;
import com.bebesfelices.api.catalog.amazon.AmazonOAuthTokenProvider;
import com.bebesfelices.api.catalog.amazon.CreatorsApiAmazonCatalogClient;
import com.bebesfelices.api.catalog.amazon.NoOpAmazonCatalogClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(AmazonCreatorsProperties.class)
public class CatalogConfig {

    @Bean
    AmazonCatalogClient amazonCatalogClient(AmazonCreatorsProperties properties) {
        if (!properties.isConfigured()) {
            return new NoOpAmazonCatalogClient();
        }

        AmazonOAuthTokenProvider tokenProvider = new AmazonOAuthTokenProvider(
                RestClient.create(),
                properties
        );
        RestClient catalogRestClient = RestClient.builder()
                .baseUrl(properties.getApiBaseUrl().toString())
                .build();
        return new CreatorsApiAmazonCatalogClient(
                catalogRestClient,
                tokenProvider,
                properties
        );
    }

    @Bean
    @Primary
    ProductCatalog productCatalog(
            ManualProductCatalog manualProductCatalog,
            AmazonCatalogClient amazonCatalogClient,
            AmazonCreatorsProperties properties
    ) {
        return new AmazonEnrichedProductCatalog(
                manualProductCatalog,
                amazonCatalogClient,
                properties
        );
    }
}
