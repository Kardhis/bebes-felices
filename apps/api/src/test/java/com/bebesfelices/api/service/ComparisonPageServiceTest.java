package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.AmazonEnrichedProductCatalog;
import com.bebesfelices.api.catalog.ManualProductCatalog;
import com.bebesfelices.api.catalog.Product;
import com.bebesfelices.api.catalog.ProductCatalog;
import com.bebesfelices.api.catalog.ProductStatus;
import com.bebesfelices.api.catalog.amazon.AmazonCreatorsProperties;
import com.bebesfelices.api.dto.ComparisonPageResponse;
import com.bebesfelices.api.dto.PageStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

class ComparisonPageServiceTest {

    private static final List<String> PRODUCT_IDS = List.of(
            "bici-chicco-red-bullet",
            "bici-kinderkraft-tove",
            "bici-kinderkraft-fly-plus-2",
            "bici-kinderkraft-goswift",
            "bici-puky-lr-m"
    );

    @Test
    void buildsThePublishedComparisonFromTheFiveRealCatalogProducts() {
        ComparisonPageResponse page = new ComparisonPageService(new ManualProductCatalog())
                .getBySlug(ComparisonPageService.BALANCE_BIKES_SLUG)
                .orElseThrow();

        assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(page.targetAge()).isEqualTo(3);
        assertThat(page.updatedAt()).isEqualTo("2026-08-13");
        assertThat(page.publishedAt()).isEqualTo("2026-08-13");
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactlyElementsOf(PRODUCT_IDS);
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::rank)
                .containsExactly(1, 2, 3, 4, 5);
        assertThat(page.entries()).allSatisfy(entry -> {
            assertThat(entry.title()).isNotBlank();
            assertThat(entry.editorialSummary()).isNotBlank();
            assertThat(entry.pros()).isNotEmpty();
            assertThat(entry.cons()).isNotEmpty();
            assertThat(entry.criteriaNotes()).isNotEmpty();
            assertThat(entry.affiliateHref()).isNull();
        });
        assertThat(page.methodology().criteria()).hasSizeGreaterThanOrEqualTo(4);
        assertThat(page.buyingGuide().sections()).isNotEmpty();
        assertThat(page.faq()).isNotEmpty();
    }

    @Test
    void returnsEmptyForAnUnknownSlug() {
        ComparisonPageService service = new ComparisonPageService(new ManualProductCatalog());

        assertThat(service.getBySlug("otra-comparativa")).isEmpty();
    }

    @Test
    void createsManualAffiliateLinksWithoutCallingCreatorsApi() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setPartnerTag("bebesfelices-21");
        properties.setProductAsins(Map.of(
                "bici-chicco-red-bullet", "B004MW55Z2",
                "bici-kinderkraft-tove", "B0CF5XRJ6S",
                "bici-kinderkraft-fly-plus-2", "B0CZTVT1DN",
                "bici-kinderkraft-goswift", "B092JTG2YL",
                "bici-puky-lr-m", "B0DJ7DS33P"
        ));
        AtomicInteger creatorsApiCalls = new AtomicInteger();
        ProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> {
                    creatorsApiCalls.incrementAndGet();
                    return Optional.empty();
                },
                properties
        );

        ComparisonPageResponse page = new ComparisonPageService(catalog)
                .getBySlug(ComparisonPageService.BALANCE_BIKES_SLUG)
                .orElseThrow();

        assertThat(creatorsApiCalls).hasValue(0);
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::affiliateHref)
                .containsExactly(
                        "https://www.amazon.es/dp/B004MW55Z2?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B0CF5XRJ6S?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B0CZTVT1DN?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B092JTG2YL?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B0DJ7DS33P?tag=bebesfelices-21"
                );
    }

    @Test
    void filtersInactiveProductsAndKeepsEditorialTitles() {
        ManualProductCatalog manual = new ManualProductCatalog();
        ProductCatalog alteredCatalog = new ProductCatalog() {
            @Override
            public Optional<Product> findById(String id) {
                return manual.findById(id).map(this::alter);
            }

            @Override
            public List<Product> findByIds(List<String> ids) {
                return manual.findByIds(ids).stream().map(this::alter).toList();
            }

            private Product alter(Product product) {
                return new Product(
                        product.id(),
                        product.source(),
                        product.asin(),
                        product.marketplace(),
                        "Título comercial no editorial",
                        product.description(),
                        product.minAge(),
                        product.maxAge(),
                        product.categories(),
                        product.id().equals("bici-kinderkraft-tove")
                                ? ProductStatus.RETIRED
                                : product.status(),
                        product.affiliateLink(),
                        product.lastReviewedAt()
                );
            }
        };

        ComparisonPageResponse page = new ComparisonPageService(alteredCatalog)
                .getBySlug(ComparisonPageService.BALANCE_BIKES_SLUG)
                .orElseThrow();

        assertThat(page.entries()).hasSize(4);
        assertThat(page.entries()).noneMatch(entry ->
                entry.productId().equals("bici-kinderkraft-tove"));
        assertThat(page.entries().get(0).title()).isEqualTo("Chicco Red Bullet");
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::rank)
                .containsExactly(1, 2, 3, 4);
    }
}
