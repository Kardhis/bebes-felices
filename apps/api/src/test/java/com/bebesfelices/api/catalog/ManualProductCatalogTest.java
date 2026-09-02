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
        });
    }

    @Test
    void researchedEducationalProductsMatchTheirDeclaredAgeAndSubcategory() {
        var products = catalog.findByIds(List.of(
                "simbolico-theo-klein-miele",
                "sensorial-emotion-bottles",
                "mundos-schleich-foal",
                "musical-hape-piano",
                "construccion-lego-classic-10698",
                "arte-crayola-pokemon-5in1",
                "experimenta-numberblocks",
                "lectura-diset-leer",
                "matematicas-sum-swamp",
                "mesa-animal-mini"
        ));

        assertThat(products).hasSize(10);
        assertThat(products).allSatisfy(product -> {
            assertThat(product.categories()).hasSize(2);
            assertThat(product.categories().get(0)).isEqualTo("Juguetes educativos");
            assertThat(product.asin()).matches("[A-Z0-9]{10}");
            assertThat(product.isAvailableForAge(product.minAge())).isTrue();
            assertThat(product.affiliateLink()).isNull();
        });
    }

    @Test
    void educationalAmazonProductsHaveUniqueIdsAndAsins() {
        var products = EducationalAmazonProducts.all();

        assertThat(products).isNotEmpty();
        assertThat(products).extracting(Product::id).doesNotHaveDuplicates();
        assertThat(products).extracting(Product::asin).doesNotHaveDuplicates();
        assertThat(products).allSatisfy(product ->
                assertThat(product.marketplace()).isEqualTo("www.amazon.es"));
    }

    @Test
    void researchedMovementProductsMatchTheirDeclaredAgeAndSubcategory() {
        var products = catalog.findByIds(List.of(
                "trepar-mamoi-triangulo-blanco",
                "trepar-little-tikes-gimnasio",
                "corre-injusa-winner-repsol",
                "corre-little-tikes-cozy-coupe"
        ));

        assertThat(products).hasSize(4);
        assertThat(products).allSatisfy(product -> {
            assertThat(product.categories()).hasSize(2);
            assertThat(product.categories().get(0)).isEqualTo("Movimiento");
            assertThat(product.asin()).matches("[A-Z0-9]{10}");
            assertThat(product.isAvailableForAge(3)).isTrue();
            assertThat(product.affiliateLink()).isNull();
        });
    }

    @Test
    void movementAmazonProductsHaveUniqueIdsAndAsins() {
        var products = MovementAmazonProducts.all();

        assertThat(products).hasSize(10);
        assertThat(products).extracting(Product::id).doesNotHaveDuplicates();
        assertThat(products).extracting(Product::asin).doesNotHaveDuplicates();
        assertThat(products).allSatisfy(product ->
                assertThat(product.marketplace()).isEqualTo("www.amazon.es"));
    }

    @Test
    void researchedAutonomyProductsMatchTheirDeclaredAgeAndSubcategory() {
        var products = catalog.findByIds(List.of(
                "cubiertos-twistshake-acero",
                "cubiertos-wmf-animales",
                "vestir-melissa-habilidades",
                "vestir-small-foot-cubo"
        ));

        assertThat(products).hasSize(4);
        assertThat(products).allSatisfy(product -> {
            assertThat(product.categories()).hasSize(2);
            assertThat(product.categories().get(0)).isEqualTo("Autonomía");
            assertThat(product.asin()).matches("[A-Z0-9]{10}");
            assertThat(product.isAvailableForAge(3)).isTrue();
            assertThat(product.affiliateLink()).isNull();
        });
    }

    @Test
    void autonomyAmazonProductsHaveUniqueIdsAndAsins() {
        var products = AutonomyAmazonProducts.all();

        assertThat(products).hasSize(10);
        assertThat(products).extracting(Product::id).doesNotHaveDuplicates();
        assertThat(products).extracting(Product::asin).doesNotHaveDuplicates();
        assertThat(products).allSatisfy(product ->
                assertThat(product.marketplace()).isEqualTo("www.amazon.es"));
    }
}
