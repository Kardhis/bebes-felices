package com.bebesfelices.api.catalog;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ManualProductCatalogTest {

    private final ManualProductCatalog catalog = new ManualProductCatalog();

    @Test
    void findsAnExistingProductById() {
        Optional<Product> found = catalog.findById("bici-sin-pedales-basica");

        assertThat(found).isPresent();
        assertThat(found.get().title()).isEqualTo("Bicicleta sin pedales básica");
    }

    @Test
    void returnsEmptyForAnUnknownId() {
        assertThat(catalog.findById("no-existe")).isEmpty();
    }

    @Test
    void findByIdsPreservesRequestedOrderAndSkipsUnknownIds() {
        List<Product> products = catalog.findByIds(List.of(
                "bici-sin-pedales-basica",
                "no-existe",
                "juego-montessori-formas"
        ));

        assertThat(products).extracting(Product::id)
                .containsExactly("bici-sin-pedales-basica", "juego-montessori-formas");
    }

    @Test
    void noProductInTheManualCatalogHasAnAffiliateLinkYet() {
        List<Product> products = catalog.findByIds(List.of(
                "juego-montessori-formas",
                "puzle-madera-animales",
                "bici-sin-pedales-basica",
                "patinete-3-ruedas",
                "torre-aprendizaje-madera",
                "set-vajilla-infantil",
                "set-construccion-magnetico",
                "juego-mesa-cooperativo",
                "kit-manualidades-natural"
        ));

        assertThat(products).hasSize(9);
        assertThat(products).allSatisfy(product -> {
            assertThat(product.affiliateLink()).isNull();
            assertThat(product.source()).isEqualTo(ProductSource.MANUAL);
            assertThat(product.asin()).isNull();
        });
    }
}
