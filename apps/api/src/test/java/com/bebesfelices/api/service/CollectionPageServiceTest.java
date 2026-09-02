package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.AmazonEnrichedProductCatalog;
import com.bebesfelices.api.catalog.ManualProductCatalog;
import com.bebesfelices.api.catalog.amazon.AmazonCreatorsProperties;
import com.bebesfelices.api.dto.PageStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionPageServiceTest {

    private final CollectionPageService service =
            new CollectionPageService(new ManualProductCatalog());

    @Test
    void publishesEveryCollectionInTheThreeYearCircuit() {
        List<String> threeYearHubSlugs = List.of(
                CollectionPageService.SCOOTERS_SLUG,
                CollectionPageService.PIKLER_SLUG,
                CollectionPageService.RIDE_ON_SLUG,
                CollectionPageService.TOWERS_SLUG,
                CollectionPageService.TABLEWARE_SLUG,
                CollectionPageService.CUTLERY_SLUG,
                CollectionPageService.DRESSING_SLUG,
                CollectionPageService.SUSTAINABLE_3_SLUG,
                CollectionPageService.GIFTS_3_SLUG
        );
        List<String> threeYearEducationalSlugs = List.of(
                CollectionPageService.MONTESSORI_SLUG,
                CollectionPageService.PUZZLES_SLUG,
                CollectionPageService.SYMBOLIC_PLAY_SLUG,
                CollectionPageService.SENSORY_TOYS_SLUG,
                CollectionPageService.SMALL_WORLDS_SLUG,
                CollectionPageService.MUSICAL_TOYS_SLUG
        );
        assertThat(service.publishedSlugs()).containsAll(threeYearHubSlugs);
        assertThat(service.publishedSlugs()).containsAll(threeYearEducationalSlugs);

        for (String slug : threeYearHubSlugs) {
            var page = service.getBySlug(slug).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.HUB_3_HREF);
            assertThat(page.products()).isNotEmpty();
            assertThat(page.relatedLinks())
                    .extracting(link -> link.href())
                    .contains(EditorialDefaults.HUB_3_HREF);
        }
        for (String slug : threeYearEducationalSlugs) {
            var page = service.getBySlug(slug).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.breadcrumbs().get(1).href())
                    .isEqualTo("/juguetes-educativos/?edad=3");
            assertThat(page.products()).isNotEmpty();
            assertThat(page.relatedLinks())
                    .extracting(link -> link.href())
                    .contains("/juguetes-educativos/?edad=3");
        }
    }

    @Test
    void publishesFourYearCollectionsWithReturnToTheFourYearHub() {
        for (String slug : List.of(
                CollectionPageService.BALANCE_BIKES_SLUG,
                CollectionPageService.GIFTS_4_SLUG,
                CollectionPageService.SUSTAINABLE_4_SLUG
        )) {
            var page = service.getBySlug(slug).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.HUB_4_HREF);
            assertThat(page.relatedLinks())
                    .extracting(link -> link.href())
                    .contains(EditorialDefaults.HUB_4_HREF);
        }
        for (String slug : List.of(
                CollectionPageService.STEM_SLUG,
                CollectionPageService.CONSTRUCTION_TOYS_SLUG,
                CollectionPageService.ARTS_CRAFTS_SLUG,
                CollectionPageService.EXPERIMENTATION_SLUG
        )) {
            var page = service.getBySlug(slug).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.breadcrumbs().get(1).href())
                    .isEqualTo("/juguetes-educativos/?edad=4");
            assertThat(page.relatedLinks())
                    .extracting(link -> link.href())
                    .contains("/juguetes-educativos/?edad=4");
        }
    }

    @Test
    void publishesFiveYearGiftAndBoardGameCollections() {
        assertThat(service.publishedSlugs()).contains(
                CollectionPageService.GIFTS_5_SLUG,
                CollectionPageService.BOARD_GAMES_SLUG,
                CollectionPageService.SUSTAINABLE_5_SLUG
        );

        var gifts = service.getBySlug(CollectionPageService.GIFTS_5_SLUG).orElseThrow();
        var games = service.getBySlug(CollectionPageService.BOARD_GAMES_SLUG).orElseThrow();
        var sustainable = service.getBySlug(CollectionPageService.SUSTAINABLE_5_SLUG).orElseThrow();

        assertThat(gifts.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(games.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(sustainable.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(gifts.products()).isNotEmpty();
        assertThat(sustainable.products()).isNotEmpty();
        assertThat(gifts.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.hubHref(5));
        assertThat(sustainable.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.hubHref(5));
        assertThat(sustainable.canonicalPath()).isEqualTo("/sostenibles/regalos-duraderos-5-anos/");
        assertThat(games.breadcrumbs().get(1).href())
                .isEqualTo("/juguetes-educativos/?edad=5");
        assertThat(games.relatedLinks())
                .extracting(link -> link.href())
                .contains("/juguetes-educativos/?edad=5");
        assertThat(gifts.products())
                .filteredOn(product -> product.title().equals("Set de construcción magnético"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo(
                            "/comparativas/mejores-juguetes-stem-5-anos/#producto-set-construccion-magnetico"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
        assertThat(games.products()).hasSize(6);
        assertThat(games.products()).allSatisfy(product ->
                assertThat(product.href()).isNull());
    }

    @Test
    void montessoriCollectionLinksToTheThreeYearComparison() {
        var page = service.getBySlug(CollectionPageService.MONTESSORI_SLUG).orElseThrow();

        assertThat(page.canonicalPath()).isEqualTo("/juguetes-educativos/juegos-montessori/");
        assertThat(page.products()).hasSize(6);
        assertThat(page.products().get(0).href())
                .isEqualTo("/comparativas/mejores-juegos-montessori-3-anos/#producto-montessori-janod-animales");
        assertThat(page.products().get(0).ctaLabel()).isEqualTo("Ver comparativa completa");
    }

    @Test
    void durableGiftsCollectionLinksToTheThreeYearComparison() {
        var page = service.getBySlug(CollectionPageService.SUSTAINABLE_3_SLUG).orElseThrow();

        assertThat(page.canonicalPath()).isEqualTo("/sostenibles/regalos-duraderos-3-anos/");
        assertThat(page.products()).hasSize(6);
        assertThat(page.products())
                .filteredOn(product -> product.title().contains("grúa"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo(
                            "/comparativas/mejores-regalos-sostenibles-3-anos/#producto-small-foot-grua"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
        assertThat(page.products())
                .filteredOn(product -> product.title().contains("SES Creative Eco"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo(
                            "/comparativas/mejores-manualidades-materiales-naturales-3-anos/#producto-arte-ses-eco-mega-7"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
        assertThat(page.relatedLinks()).extracting(link -> link.href())
                .contains(
                        "/comparativas/mejores-regalos-duraderos-3-anos/",
                        "/comparativas/mejores-regalos-sostenibles-3-anos/"
                );
    }

    @Test
    void giftCollectionIncludesTheThreeYearComparisons() {
        var page = service.getBySlug(CollectionPageService.GIFTS_3_SLUG).orElseThrow();

        assertThat(page.canonicalPath()).isEqualTo("/regalos/ideas-regalo-3-anos/");
        assertThat(page.products()).extracting(product -> product.href())
                .contains(
                        "/comparativas/mejores-juegos-montessori-madera-3-anos/#producto-juego-montessori-formas",
                        "/comparativas/mejores-bicicletas-sin-pedales-3-anos/#producto-bici-chicco-red-bullet",
                        "/comparativas/mejores-patinetes-3-anos/#producto-patinete-yvolution-y-glider"
                );
        assertThat(page.products())
                .extracting(product -> product.ctaLabel())
                .containsOnly("Ver comparativa completa");
    }

    @Test
    void fourYearGiftCollectionIncludesTheBoardGameComparison() {
        var page = service.getBySlug(CollectionPageService.GIFTS_4_SLUG).orElseThrow();

        assertThat(page.canonicalPath()).isEqualTo("/regalos/ideas-regalo-4-anos/");
        assertThat(page.products())
                .filteredOn(product -> product.title().contains("Frutal"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo(
                            "/comparativas/mejores-juegos-de-mesa-4-anos/#producto-juego-mesa-el-frutal-mini"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
        assertThat(page.products())
                .filteredOn(product -> product.title().contains("YOLEO"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo(
                            "/comparativas/mejores-torres-aprendizaje-4-anos/#producto-torre-yoleo-transformer"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
    }

    @Test
    void returnsEmptyForAnUnknownSlug() {
        assertThat(service.getBySlug("no-existe")).isEmpty();
    }

    @Test
    void newEducationalCollectionsContainSixMatchingProductsWithAmazonLinks() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setPartnerTag("bebesfelice0c-21");
        CollectionPageService educationalService = new CollectionPageService(
                new AmazonEnrichedProductCatalog(
                        new ManualProductCatalog(),
                        (asin, marketplace) -> Optional.empty(),
                        properties
                )
        );
        var expectedCategories = java.util.Map.of(
                CollectionPageService.SYMBOLIC_PLAY_SLUG, "Juego simbólico",
                CollectionPageService.SENSORY_TOYS_SLUG, "Sensoriales",
                CollectionPageService.SMALL_WORLDS_SLUG, "Pequeños mundos",
                CollectionPageService.MUSICAL_TOYS_SLUG, "Musicales",
                CollectionPageService.CONSTRUCTION_TOYS_SLUG, "Construcción",
                CollectionPageService.ARTS_CRAFTS_SLUG, "Arte y manualidades",
                CollectionPageService.LITERACY_SLUG, "Lectoescritura"
        );
        var mixedCategorySlugs = List.of(
                CollectionPageService.MONTESSORI_SLUG,
                CollectionPageService.PUZZLES_SLUG,
                CollectionPageService.STEM_SLUG,
                CollectionPageService.BOARD_GAMES_SLUG,
                CollectionPageService.EXPERIMENTATION_SLUG,
                CollectionPageService.MATH_LOGIC_SLUG,
                CollectionPageService.COOPERATIVE_SEL_SLUG
        );

        expectedCategories.forEach((slug, category) -> {
            var page = educationalService.getBySlug(slug).orElseThrow();
            assertThat(page.products()).hasSize(6);
            assertThat(page.breadcrumbs().get(1).href())
                    .isEqualTo("/juguetes-educativos/?edad=" + page.hubAge());
            assertThat(page.products()).allSatisfy(product -> {
                assertThat(product.category()).isEqualTo(category);
                assertThat(product.href()).isNull();
                assertThat(product.ctaLabel()).isNull();
                assertThat(product.affiliateHref())
                        .startsWith("https://www.amazon.es/dp/")
                        .contains("tag=bebesfelice0c-21");
            });
        });
        for (String slug : mixedCategorySlugs) {
            var page = educationalService.getBySlug(slug).orElseThrow();
            assertThat(page.products()).hasSize(6);
            assertThat(page.breadcrumbs().get(1).href())
                    .isEqualTo("/juguetes-educativos/?edad=" + page.hubAge());
            assertThat(page.products()).allSatisfy(product ->
                    assertThat(product.affiliateHref())
                            .startsWith("https://www.amazon.es/dp/")
                            .contains("tag=bebesfelice0c-21"));
        }
    }
}
