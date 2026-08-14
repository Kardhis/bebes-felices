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
        assertThat(page.breadcrumbs()).hasSize(3);
        assertThat(page.breadcrumbs().get(1).label()).isEqualTo("3 años");
        assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/3-anos/");
        assertThat(page.relatedLinks().get(0).href()).isEqualTo("/por-edad/3-anos/");
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

    @Test
    void buildsThePublishedBoardGameComparisonForFourYearOlds() {
        ComparisonPageResponse page = new ComparisonPageService(new ManualProductCatalog())
                .getBySlug(ComparisonPageService.BOARD_GAMES_SLUG)
                .orElseThrow();

        assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(page.targetAge()).isEqualTo(4);
        assertThat(page.updatedAt()).isEqualTo("2026-08-14");
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "juego-mesa-el-frutal-mini",
                        "juego-mesa-unicornio-tesoro",
                        "juego-mesa-animal-sobre-animal",
                        "juego-mesa-dobble-kids",
                        "juego-mesa-unicornio-fiesta-rosalie"
                );
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::rank)
                .containsExactly(1, 2, 3, 4, 5);
        assertThat(page.entries()).allSatisfy(entry -> {
            assertThat(entry.title()).isNotBlank();
            assertThat(entry.pros()).isNotEmpty();
            assertThat(entry.cons()).isNotEmpty();
            assertThat(entry.affiliateHref()).isNull();
        });
        assertThat(page.breadcrumbs().get(1).label()).isEqualTo("4 años");
        assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/4-anos/");
        assertThat(page.relatedLinks().get(0).href()).isEqualTo("/por-edad/4-anos/");
    }

    @Test
    void buildsThePublishedScooterComparisonForFourYearOlds() {
        ComparisonPageResponse page = new ComparisonPageService(new ManualProductCatalog())
                .getBySlug(ComparisonPageService.SCOOTERS_SLUG)
                .orElseThrow();

        assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(page.targetAge()).isEqualTo(4);
        assertThat(page.updatedAt()).isEqualTo("2026-08-14");
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "patinete-micro-mini-deluxe",
                        "patinete-molto-maxi",
                        "patinete-globber-junior-foldable",
                        "patinete-globber-master-lights",
                        "triciclo-chicco-u-go"
                );
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::rank)
                .containsExactly(1, 2, 3, 4, 5);
        assertThat(page.entries()).allSatisfy(entry -> {
            assertThat(entry.title()).isNotBlank();
            assertThat(entry.pros()).isNotEmpty();
            assertThat(entry.cons()).isNotEmpty();
            assertThat(entry.affiliateHref()).isNull();
        });
        assertThat(page.header().h1()).isEqualTo("Mejores patinetes y triciclos para 4 años");
        assertThat(page.breadcrumbs().get(1).label()).isEqualTo("4 años");
        assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/4-anos/");
        assertThat(page.relatedLinks().get(0).href()).isEqualTo("/por-edad/4-anos/");
    }

    @Test
    void buildsThePublishedFourYearAutonomyAndSustainableComparisons() {
        ComparisonPageService service = new ComparisonPageService(new ManualProductCatalog());

        ComparisonPageResponse towers = service.getBySlug(ComparisonPageService.TOWERS_SLUG).orElseThrow();
        ComparisonPageResponse tableware = service.getBySlug(ComparisonPageService.TABLEWARE_SLUG).orElseThrow();
        ComparisonPageResponse sustainable = service.getBySlug(ComparisonPageService.SUSTAINABLE_SLUG).orElseThrow();

        assertThat(towers.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "torre-yoleo-transformer",
                        "torre-bianconiglio-evo",
                        "torre-kleiner-riese",
                        "torre-bey-co",
                        "torre-bianconiglio-transformer"
                );
        assertThat(tableware.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "vajilla-twistshake-dividido",
                        "vajilla-stor-mickey",
                        "vaso-munchkin-miracle-360",
                        "vajilla-fun-house",
                        "cuenco-twistshake-tapa"
                );
        assertThat(sustainable.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "cuentas-melissa-doug",
                        "plantoys-ata-zapato",
                        "haba-puzles-cuatro-estaciones",
                        "small-foot-grua",
                        "green-toys-construccion"
                );
        assertThat(List.of(towers, tableware, sustainable)).allSatisfy(page -> {
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.targetAge()).isEqualTo(4);
            assertThat(page.entries()).hasSize(5);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/4-anos/");
            assertThat(page.entries()).allSatisfy(entry -> assertThat(entry.affiliateHref()).isNull());
        });
    }

    @Test
    void createsManualAffiliateLinksForBoardGamesWithoutCallingCreatorsApi() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setPartnerTag("bebesfelices-21");
        properties.setProductAsins(Map.of(
                "juego-mesa-el-frutal-mini", "B08R3YTDPQ",
                "juego-mesa-unicornio-tesoro", "B01MRA4YCR",
                "juego-mesa-animal-sobre-animal", "B00D6J9SJQ",
                "juego-mesa-dobble-kids", "B00OM7VIC6",
                "juego-mesa-unicornio-fiesta-rosalie", "B06XCLF568"
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
                .getBySlug(ComparisonPageService.BOARD_GAMES_SLUG)
                .orElseThrow();

        assertThat(creatorsApiCalls).hasValue(0);
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::affiliateHref)
                .containsExactly(
                        "https://www.amazon.es/dp/B08R3YTDPQ?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B01MRA4YCR?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B00D6J9SJQ?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B00OM7VIC6?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B06XCLF568?tag=bebesfelices-21"
                );
    }

    @Test
    void createsManualAffiliateLinksForScootersWithoutCallingCreatorsApi() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setPartnerTag("bebesfelices-21");
        properties.setProductAsins(Map.of(
                "patinete-micro-mini-deluxe", "B09PRNX4HX",
                "patinete-molto-maxi", "B09WMPSMM4",
                "patinete-globber-junior-foldable", "B09CQDGBJ3",
                "patinete-globber-master-lights", "B08G19X6GK",
                "triciclo-chicco-u-go", "B00URLWKYG"
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
                .getBySlug(ComparisonPageService.SCOOTERS_SLUG)
                .orElseThrow();

        assertThat(creatorsApiCalls).hasValue(0);
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::affiliateHref)
                .containsExactly(
                        "https://www.amazon.es/dp/B09PRNX4HX?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B09WMPSMM4?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B09CQDGBJ3?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B08G19X6GK?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B00URLWKYG?tag=bebesfelices-21"
                );
    }
}
