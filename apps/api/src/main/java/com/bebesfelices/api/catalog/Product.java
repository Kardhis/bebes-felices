package com.bebesfelices.api.catalog;

import java.time.LocalDate;
import java.util.List;

/**
 * Producto o recomendación normalizada del catálogo interno.
 * <p>
 * El modelo es compatible tanto con productos introducidos manualmente
 * ({@code source == MANUAL}) como con productos que en el futuro procedan
 * de Amazon ({@code source == AMAZON}) a través de
 * {@link com.bebesfelices.api.catalog.amazon.AmazonCatalogClient}. Las
 * páginas y componentes no necesitan distinguir el origen: solo consumen
 * este modelo ya normalizado.
 */
public record Product(
        String id,
        ProductSource source,
        String asin,
        String marketplace,
        String title,
        String description,
        int minAge,
        int maxAge,
        List<String> categories,
        ProductStatus status,
        AffiliateLink affiliateLink,
        LocalDate lastReviewedAt
) {

    public boolean isAvailableForAge(int age) {
        return status == ProductStatus.ACTIVE && age >= minAge && age <= maxAge;
    }

    public boolean hasValidatedAffiliateLink() {
        return status == ProductStatus.ACTIVE && affiliateLink != null;
    }
}
