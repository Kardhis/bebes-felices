package com.bebesfelices.api.catalog;

import com.bebesfelices.api.catalog.amazon.AmazonCatalogClient;
import com.bebesfelices.api.catalog.amazon.AmazonCreatorsProperties;
import com.bebesfelices.api.catalog.amazon.AmazonProductSnapshot;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Añade datos comerciales verificados sin alterar el contenido editorial.
 */
public class AmazonEnrichedProductCatalog implements ProductCatalog {

    private static final Duration FAILED_LOOKUP_TTL = Duration.ofMinutes(5);

    private final ProductCatalog editorialCatalog;
    private final AmazonCatalogClient amazonCatalogClient;
    private final AmazonCreatorsProperties properties;
    private final Clock clock;
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

    public AmazonEnrichedProductCatalog(
            ProductCatalog editorialCatalog,
            AmazonCatalogClient amazonCatalogClient,
            AmazonCreatorsProperties properties
    ) {
        this(editorialCatalog, amazonCatalogClient, properties, Clock.systemUTC());
    }

    AmazonEnrichedProductCatalog(
            ProductCatalog editorialCatalog,
            AmazonCatalogClient amazonCatalogClient,
            AmazonCreatorsProperties properties,
            Clock clock
    ) {
        this.editorialCatalog = editorialCatalog;
        this.amazonCatalogClient = amazonCatalogClient;
        this.properties = properties;
        this.clock = clock;
    }

    @Override
    public Optional<Product> findById(String id) {
        return editorialCatalog.findById(id).map(this::enrich);
    }

    @Override
    public List<Product> findByIds(List<String> ids) {
        return editorialCatalog.findByIds(ids).stream()
                .map(this::enrich)
                .toList();
    }

    private Product enrich(Product product) {
        String asin = properties.asinFor(product.id());
        if (asin == null) {
            return product;
        }
        if (!properties.isConfigured()) {
            return withManualAffiliateLink(product, asin);
        }

        Optional<AmazonProductSnapshot> snapshot = cachedSnapshot(asin);
        if (snapshot.isEmpty()) {
            return withManualAffiliateLink(product, asin);
        }

        try {
            AmazonProductSnapshot amazonProduct = snapshot.orElseThrow();
            return new Product(
                    product.id(),
                    ProductSource.AMAZON,
                    amazonProduct.asin(),
                    amazonProduct.marketplace(),
                    product.title(),
                    product.description(),
                    product.minAge(),
                    product.maxAge(),
                    product.categories(),
                    product.status(),
                    new AffiliateLink(amazonProduct.detailPageUrl(), "Amazon"),
                    product.lastReviewedAt()
            );
        } catch (IllegalArgumentException invalidAffiliateUrl) {
            return withManualAffiliateLink(product, asin);
        }
    }

    private Product withManualAffiliateLink(Product product, String asin) {
        if (properties.getPartnerTag() == null
                || properties.getPartnerTag().isBlank()
                || !asin.matches("[A-Z0-9]{10}")) {
            return product;
        }

        String partnerTag = URLEncoder.encode(
                properties.getPartnerTag().trim(),
                StandardCharsets.UTF_8
        );
        String detailPageUrl = "https://" + properties.getMarketplace()
                + "/dp/" + asin + "?tag=" + partnerTag;
        try {
            return new Product(
                    product.id(),
                    ProductSource.MANUAL,
                    asin,
                    properties.getMarketplace(),
                    product.title(),
                    product.description(),
                    product.minAge(),
                    product.maxAge(),
                    product.categories(),
                    product.status(),
                    new AffiliateLink(detailPageUrl, "Amazon"),
                    product.lastReviewedAt()
            );
        } catch (IllegalArgumentException invalidAffiliateUrl) {
            return product;
        }
    }

    private Optional<AmazonProductSnapshot> cachedSnapshot(String asin) {
        String key = properties.getMarketplace() + ":" + asin;
        Instant now = clock.instant();
        CacheEntry current = cache.get(key);
        if (current != null && current.expiresAt().isAfter(now)) {
            return current.snapshot();
        }

        Optional<AmazonProductSnapshot> fetched =
                amazonCatalogClient.fetchByAsin(asin, properties.getMarketplace());
        Duration ttl = fetched.isPresent()
                ? properties.getProductCacheTtl()
                : FAILED_LOOKUP_TTL;
        cache.put(key, new CacheEntry(fetched, now.plus(ttl)));
        return fetched;
    }

    private record CacheEntry(
            Optional<AmazonProductSnapshot> snapshot,
            Instant expiresAt
    ) {
    }
}
