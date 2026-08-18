package com.bebesfelices.api.catalog;

import com.bebesfelices.api.catalog.amazon.AmazonCreatorsProperties;
import com.bebesfelices.api.catalog.amazon.AmazonProductSnapshot;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

class AmazonEnrichedProductCatalogTest {

    @Test
    void addsAValidatedAffiliateLinkAndPreservesEditorialContent() {
        AmazonCreatorsProperties properties = configuredProperties();
        AmazonEnrichedProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> Optional.of(new AmazonProductSnapshot(
                        asin,
                        marketplace,
                        "Título comercial que no debe sustituir al editorial",
                        "https://www.amazon.es/dp/" + asin + "?tag=bebesfelices-21",
                        "https://m.media-amazon.com/product.jpg"
                )),
                properties
        );

        Product product = catalog.findById("bici-sin-pedales-basica").orElseThrow();

        assertThat(product.source()).isEqualTo(ProductSource.AMAZON);
        assertThat(product.asin()).isEqualTo("B012345678");
        assertThat(product.title()).isEqualTo("Bicicleta sin pedales básica");
        assertThat(product.affiliateLink().url())
                .isEqualTo("https://www.amazon.es/dp/B012345678?tag=bebesfelices-21");
    }

    @Test
    void usesTheManualAffiliateLinkWhenAmazonFailsOrTheUrlIsInvalid() {
        AmazonCreatorsProperties properties = configuredProperties();
        AmazonEnrichedProductCatalog unavailableCatalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> Optional.empty(),
                properties
        );
        AmazonEnrichedProductCatalog invalidUrlCatalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> Optional.of(new AmazonProductSnapshot(
                        asin,
                        marketplace,
                        "Título",
                        "https://example.com/product",
                        null
                )),
                properties
        );

        assertThat(unavailableCatalog.findById("bici-sin-pedales-basica").orElseThrow())
                .satisfies(product -> {
                    assertThat(product.source()).isEqualTo(ProductSource.MANUAL);
                    assertThat(product.affiliateLink().url())
                            .isEqualTo("https://www.amazon.es/dp/B012345678?tag=bebesfelices-21");
                });
        assertThat(invalidUrlCatalog.findById("bici-sin-pedales-basica").orElseThrow())
                .satisfies(product -> {
                    assertThat(product.source()).isEqualTo(ProductSource.MANUAL);
                    assertThat(product.affiliateLink().url())
                            .isEqualTo("https://www.amazon.es/dp/B012345678?tag=bebesfelices-21");
                });
    }

    @Test
    void createsTheManualAffiliateLinkWithoutCreatorsApiCredentials() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setPartnerTag("bebesfelice0c-21");
        properties.setProductAsins(Map.of(
                "juego-montessori-formas", "B00005RF5G",
                "bici-sin-pedales-basica", "B004MW55Z2"
        ));
        AtomicInteger calls = new AtomicInteger();
        AmazonEnrichedProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> {
                    calls.incrementAndGet();
                    return Optional.empty();
                },
                properties
        );

        Product montessori = catalog.findById("juego-montessori-formas").orElseThrow();
        Product bike = catalog.findById("bici-sin-pedales-basica").orElseThrow();

        assertThat(calls).hasValue(0);
        assertThat(montessori.affiliateLink().url())
                .isEqualTo("https://www.amazon.es/dp/B00005RF5G?tag=bebesfelice0c-21");
        assertThat(bike.affiliateLink().url())
                .isEqualTo("https://www.amazon.es/dp/B004MW55Z2?tag=bebesfelice0c-21");
    }

    @Test
    void cachesAmazonResults() {
        AmazonCreatorsProperties properties = configuredProperties();
        AtomicInteger calls = new AtomicInteger();
        AmazonEnrichedProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> {
                    calls.incrementAndGet();
                    return Optional.of(new AmazonProductSnapshot(
                            asin,
                            marketplace,
                            "Título",
                            "https://www.amazon.es/dp/" + asin,
                            null
                    ));
                },
                properties
        );

        catalog.findById("bici-sin-pedales-basica");
        catalog.findById("bici-sin-pedales-basica");

        assertThat(calls).hasValue(1);
    }

    private AmazonCreatorsProperties configuredProperties() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setCredentialId("credential-id");
        properties.setCredentialSecret("credential-secret");
        properties.setPartnerTag("bebesfelices-21");
        properties.setProductAsins(Map.of("bici-sin-pedales-basica", "B012345678"));
        return properties;
    }
}
