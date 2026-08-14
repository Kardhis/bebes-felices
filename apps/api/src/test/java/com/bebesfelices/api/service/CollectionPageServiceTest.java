package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.ManualProductCatalog;
import com.bebesfelices.api.dto.PageStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionPageServiceTest {

    private final CollectionPageService service =
            new CollectionPageService(new ManualProductCatalog());

    @Test
    void publishesEveryCollectionInTheThreeYearCircuit() {
        assertThat(service.publishedSlugs()).containsExactly(
                CollectionPageService.MONTESSORI_SLUG,
                CollectionPageService.PUZZLES_SLUG,
                CollectionPageService.SCOOTERS_SLUG,
                CollectionPageService.TOWERS_SLUG,
                CollectionPageService.TABLEWARE_SLUG,
                CollectionPageService.SUSTAINABLE_SLUG,
                CollectionPageService.GIFTS_3_SLUG
        );

        for (String slug : service.publishedSlugs()) {
            var page = service.getBySlug(slug).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.HUB_3_HREF);
            assertThat(page.products()).isNotEmpty();
            assertThat(page.buyingCriteria()).isNotEmpty();
            assertThat(page.faq()).isNotEmpty();
            assertThat(page.relatedLinks())
                    .extracting(link -> link.href())
                    .contains(EditorialDefaults.HUB_3_HREF);
            assertThat(page.products()).allSatisfy(product ->
                    assertThat(product.affiliateHref()).isNull());
        }
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
    void returnsEmptyForAnUnknownSlug() {
        assertThat(service.getBySlug("no-existe")).isEmpty();
    }
}
