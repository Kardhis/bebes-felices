package com.bebesfelices.api.catalog;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void isAvailableForAgeOnlyWithinRangeAndWhenActive() {
        Product product = manualProduct(3, 5, ProductStatus.ACTIVE, null);

        assertThat(product.isAvailableForAge(2)).isFalse();
        assertThat(product.isAvailableForAge(3)).isTrue();
        assertThat(product.isAvailableForAge(4)).isTrue();
        assertThat(product.isAvailableForAge(5)).isTrue();
        assertThat(product.isAvailableForAge(6)).isFalse();
    }

    @Test
    void isNotAvailableForAgeWhenRetiredOrPendingReview() {
        Product retired = manualProduct(3, 5, ProductStatus.RETIRED, null);
        Product pending = manualProduct(3, 5, ProductStatus.PENDING_REVIEW, null);

        assertThat(retired.isAvailableForAge(4)).isFalse();
        assertThat(pending.isAvailableForAge(4)).isFalse();
    }

    @Test
    void hasValidatedAffiliateLinkOnlyWhenActiveAndLinkPresent() {
        AffiliateLink link = new AffiliateLink("https://www.amazon.es/dp/EXAMPLE", "Amazon");

        Product withLinkActive = manualProduct(3, 5, ProductStatus.ACTIVE, link);
        Product withoutLink = manualProduct(3, 5, ProductStatus.ACTIVE, null);
        Product withLinkButRetired = manualProduct(3, 5, ProductStatus.RETIRED, link);

        assertThat(withLinkActive.hasValidatedAffiliateLink()).isTrue();
        assertThat(withoutLink.hasValidatedAffiliateLink()).isFalse();
        assertThat(withLinkButRetired.hasValidatedAffiliateLink()).isFalse();
    }

    private Product manualProduct(int minAge, int maxAge, ProductStatus status, AffiliateLink link) {
        return new Product(
                "test-product",
                ProductSource.MANUAL,
                null,
                null,
                "Producto de prueba",
                "Descripción de prueba",
                minAge,
                maxAge,
                List.of("Categoría de prueba"),
                status,
                link,
                LocalDate.of(2026, 1, 1)
        );
    }
}
