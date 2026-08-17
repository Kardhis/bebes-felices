package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.ManualProductCatalog;
import com.bebesfelices.api.dto.PageStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionPageServiceTest {

    private final CollectionPageService service =
            new CollectionPageService(new ManualProductCatalog());

    @Test
    void publishesEveryCollectionInTheThreeYearCircuit() {
        List<String> threeYearSlugs = List.of(
                CollectionPageService.MONTESSORI_SLUG,
                CollectionPageService.PUZZLES_SLUG,
                CollectionPageService.SCOOTERS_SLUG,
                CollectionPageService.TOWERS_SLUG,
                CollectionPageService.TABLEWARE_SLUG,
                CollectionPageService.SUSTAINABLE_SLUG,
                CollectionPageService.GIFTS_3_SLUG
        );
        assertThat(service.publishedSlugs()).containsAll(threeYearSlugs);

        for (String slug : threeYearSlugs) {
            var page = service.getBySlug(slug).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.HUB_3_HREF);
            assertThat(page.products()).isNotEmpty();
            assertThat(page.relatedLinks())
                    .extracting(link -> link.href())
                    .contains(EditorialDefaults.HUB_3_HREF);
        }
    }

    @Test
    void publishesFourYearCollectionsWithReturnToTheFourYearHub() {
        for (String slug : List.of(
                CollectionPageService.STEM_SLUG,
                CollectionPageService.BALANCE_BIKES_SLUG,
                CollectionPageService.GIFTS_4_SLUG
        )) {
            var page = service.getBySlug(slug).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.HUB_4_HREF);
            assertThat(page.relatedLinks())
                    .extracting(link -> link.href())
                    .contains(EditorialDefaults.HUB_4_HREF);
        }
    }

    @Test
    void publishesFiveYearGiftAndBoardGameCollections() {
        assertThat(service.publishedSlugs()).contains(
                CollectionPageService.GIFTS_5_SLUG,
                CollectionPageService.BOARD_GAMES_SLUG
        );

        var gifts = service.getBySlug(CollectionPageService.GIFTS_5_SLUG).orElseThrow();
        var games = service.getBySlug(CollectionPageService.BOARD_GAMES_SLUG).orElseThrow();

        assertThat(List.of(gifts, games)).allSatisfy(page -> {
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.hubHref(5));
            assertThat(page.products()).isNotEmpty();
        });
        assertThat(gifts.products())
                .filteredOn(product -> product.title().equals("Set de construcción magnético"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo(
                            "/comparativas/mejores-juguetes-stem-5-anos/#producto-set-construccion-magnetico"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
        assertThat(games.products()).singleElement().satisfies(product ->
                assertThat(product.href()).isEqualTo("/analisis/juego-mesa-cooperativo/"));
    }

    @Test
    void montessoriCollectionLinksToItsAnalysis() {
        var page = service.getBySlug(CollectionPageService.MONTESSORI_SLUG).orElseThrow();

        assertThat(page.canonicalPath()).isEqualTo("/juguetes-educativos/juegos-montessori/");
        assertThat(page.products()).hasSize(1);
        assertThat(page.products().get(0).href())
                .isEqualTo("/analisis/juego-montessori-formas/");
        assertThat(page.products().get(0).ctaLabel()).isEqualTo("Ver análisis completo");
    }

    @Test
    void giftCollectionIncludesTheBalanceBikeComparisonInsteadOfAMissingAnalysis() {
        var page = service.getBySlug(CollectionPageService.GIFTS_3_SLUG).orElseThrow();

        assertThat(page.canonicalPath()).isEqualTo("/regalos/ideas-regalo-3-anos/");
        assertThat(page.products()).extracting(product -> product.href())
                .contains(
                        "/analisis/juego-montessori-formas/",
                        "/comparativas/mejores-bicicletas-sin-pedales-3-anos/#producto-bici-chicco-red-bullet"
                );
        assertThat(page.products())
                .filteredOn(product -> product.title().contains("Chicco"))
                .extracting(product -> product.ctaLabel())
                .containsExactly("Ver comparativa completa");
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
}
