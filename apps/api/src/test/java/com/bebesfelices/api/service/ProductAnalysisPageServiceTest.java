package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.ManualProductCatalog;
import com.bebesfelices.api.dto.PageStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductAnalysisPageServiceTest {

    private final ProductAnalysisPageService service =
            new ProductAnalysisPageService(new ManualProductCatalog());

    @Test
    void publishesTheSixFeaturedAnalysesExceptChicco() {
        assertThat(service.publishedProductIds())
                .containsExactlyElementsOf(ProductAnalysisPageService.PUBLISHED_PRODUCT_IDS)
                .doesNotContain("bici-chicco-red-bullet");

        for (String productId : service.publishedProductIds()) {
            var page = service.getByProductId(productId).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.canonicalPath()).isEqualTo("/analisis/" + productId + "/");
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.HUB_3_HREF);
            assertThat(page.pros()).isNotEmpty();
            assertThat(page.cons()).isNotEmpty();
            assertThat(page.safetyNotes()).isNotEmpty();
            assertThat(page.buyingChecks()).isNotEmpty();
            assertThat(page.affiliateHref()).isNull();
            assertThat(page.relatedLinks())
                    .extracting(link -> link.href())
                    .contains(EditorialDefaults.HUB_3_HREF);
            assertThat(page.relatedLinks().get(0).href()).startsWith("/");
            assertThat(page.relatedLinks().get(0).href()).doesNotContain("/analisis/");
        }
    }

    @Test
    void returnsEmptyForProductsOutsideThisPhase() {
        assertThat(service.getByProductId("bici-chicco-red-bullet")).isEmpty();
        assertThat(service.getByProductId("bici-sin-pedales-basica")).isEmpty();
        assertThat(service.getByProductId("no-existe")).isEmpty();
    }
}
