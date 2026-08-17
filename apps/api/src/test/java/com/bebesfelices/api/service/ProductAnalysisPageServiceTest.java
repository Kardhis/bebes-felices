package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.ManualProductCatalog;
import com.bebesfelices.api.dto.PageStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductAnalysisPageServiceTest {

    private final ProductAnalysisPageService service =
            new ProductAnalysisPageService(new ManualProductCatalog());

    @Test
    void publishesTheFeaturedAnalysesExceptComparisonSpotlights() {
        assertThat(service.publishedProductIds())
                .containsExactlyElementsOf(ProductAnalysisPageService.PUBLISHED_PRODUCT_IDS)
                .doesNotContain(
                        "bici-chicco-red-bullet",
                        "juego-mesa-el-frutal-mini",
                        "patinete-micro-mini-deluxe",
                        "torre-yoleo-transformer",
                        "vajilla-twistshake-dividido",
                        "cuentas-melissa-doug"
                );

        for (String productId : List.of(
                "juego-montessori-formas",
                "puzle-madera-animales",
                "patinete-3-ruedas",
                "torre-aprendizaje-madera",
                "set-vajilla-infantil",
                "kit-manualidades-natural"
        )) {
            var page = service.getByProductId(productId).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.HUB_3_HREF);
            assertThat(page.affiliateHref()).isNull();
        }
    }

    @Test
    void publishesFourYearAnalysesWithReturnToTheFourYearHub() {
        for (String productId : List.of("set-construccion-magnetico", "bici-sin-pedales-basica")) {
            var page = service.getByProductId(productId).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.HUB_4_HREF);
            assertThat(page.relatedLinks())
                    .extracting(link -> link.href())
                    .contains(EditorialDefaults.HUB_4_HREF);
        }
    }

    @Test
    void publishesTheFiveYearCooperativeBoardGameAnalysis() {
        var page = service.getByProductId("juego-mesa-cooperativo").orElseThrow();

        assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(page.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.hubHref(5));
        assertThat(page.relatedLinks()).extracting(link -> link.href())
                .contains(
                        EditorialDefaults.hubHref(5),
                        "/juguetes-educativos/juegos-de-mesa/"
                );
        assertThat(page.affiliateHref()).isNull();
    }

    @Test
    void returnsEmptyForProductsOutsideThisPhase() {
        assertThat(service.getByProductId("bici-chicco-red-bullet")).isEmpty();
        assertThat(service.getByProductId("juego-mesa-el-frutal-mini")).isEmpty();
        assertThat(service.getByProductId("patinete-micro-mini-deluxe")).isEmpty();
        assertThat(service.getByProductId("torre-yoleo-transformer")).isEmpty();
        assertThat(service.getByProductId("no-existe")).isEmpty();
    }
}
