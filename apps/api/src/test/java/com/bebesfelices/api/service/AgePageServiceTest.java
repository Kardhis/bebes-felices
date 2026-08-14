package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.AmazonEnrichedProductCatalog;
import com.bebesfelices.api.catalog.ManualProductCatalog;
import com.bebesfelices.api.catalog.amazon.AmazonCreatorsProperties;
import com.bebesfelices.api.catalog.amazon.AmazonProductSnapshot;
import com.bebesfelices.api.dto.AgePageResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AgePageServiceTest {

    private static final String BALANCE_BIKES_HREF =
            "/comparativas/mejores-bicicletas-sin-pedales-3-anos/";
    private final AgePageService service = new AgePageService(new ManualProductCatalog());

    @ParameterizedTest
    @ValueSource(strings = {"3-anos", "4-anos", "5-anos"})
    void returnsAResponseForEachValidSlug(String slug) {
        Optional<AgePageResponse> page = service.getBySlug(slug);

        assertThat(page).isPresent();
        assertThat(page.get().slug()).isEqualTo(slug);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2-anos", "6-anos", "3-anios", ""})
    void returnsEmptyForAnyOtherSlug(String slug) {
        assertThat(service.getBySlug(slug)).isEmpty();
    }

    @Test
    void differentiatesContentAcrossAges() {
        AgePageResponse page3 = service.getBySlug("3-anos").orElseThrow();
        AgePageResponse page4 = service.getBySlug("4-anos").orElseThrow();
        AgePageResponse page5 = service.getBySlug("5-anos").orElseThrow();

        assertThat(page3.header().h1()).isNotEqualTo(page4.header().h1());
        assertThat(page3.header().introductionParagraphs()).isNotEqualTo(page4.header().introductionParagraphs());
        assertThat(page4.header().introductionParagraphs()).isNotEqualTo(page5.header().introductionParagraphs());
        assertThat(page3.header().introductionParagraphs()).hasSizeGreaterThan(1);

        assertThat(page3.seo().canonicalUrl()).isEqualTo("https://bebesfelices.es/por-edad/3-anos/");
        assertThat(page4.seo().canonicalUrl()).isEqualTo("https://bebesfelices.es/por-edad/4-anos/");

        // La selección destacada varía según disponibilidad por edad en el catálogo.
        List<String> titles3 = page3.featuredSelection().stream().map(AgePageResponse.FeaturedProduct::title).toList();
        List<String> titles5 = page5.featuredSelection().stream().map(AgePageResponse.FeaturedProduct::title).toList();
        assertThat(titles3).isNotEqualTo(titles5);
    }

    @Test
    void featuredSelectionSizeStaysWithinEditorialRange() {
        for (String slug : List.of("3-anos", "4-anos", "5-anos")) {
            AgePageResponse page = service.getBySlug(slug).orElseThrow();
            assertThat(page.featuredSelection().size()).isBetween(5, 10);
        }
    }

    @Test
    void closesTheBalanceBikeCircuitForThreeYearOlds() {
        AgePageResponse page = service.getBySlug("3-anos").orElseThrow();

        AgePageResponse.NeedGroup movement = page.optionsByNeed().stream()
                .filter(group -> group.anchor().equals("#para-moverse"))
                .findFirst()
                .orElseThrow();
        assertThat(movement.items().get(0).href()).isEqualTo(BALANCE_BIKES_HREF);
        assertThat(page.quickNavigation())
                .anySatisfy(item -> {
                    assertThat(item.label()).isEqualTo("Comparativa bicicletas");
                    assertThat(item.anchor()).isEqualTo("#rankings-destacados");
                });

        assertThat(page.featuredSelection())
                .noneMatch(product -> product.title().equals("Bicicleta sin pedales básica"))
                .anySatisfy(product -> {
                    assertThat(product.title()).isEqualTo("Chicco Red Bullet");
                    assertThat(product.href()).isEqualTo(
                            BALANCE_BIKES_HREF + "#producto-bici-chicco-red-bullet"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
        assertThat(page.featuredRankings())
                .extracting(link -> link.href())
                .containsExactly(BALANCE_BIKES_HREF);
        assertThat(page.featuredSelection())
                .filteredOn(product -> !product.title().equals("Chicco Red Bullet"))
                .allSatisfy(product -> {
                    assertThat(product.ctaLabel()).isEqualTo("Ver análisis completo");
                    assertThat(product.href()).startsWith("/analisis/");
                });
    }

    @Test
    void keepsFourAndFiveYearOldContentUnchanged() {
        AgePageResponse page4 = service.getBySlug("4-anos").orElseThrow();
        AgePageResponse page5 = service.getBySlug("5-anos").orElseThrow();
        List<String> defaultQuickNavigation = List.of(
                "Selección destacada",
                "Juguetes educativos",
                "Movimiento",
                "Autonomía",
                "Regalos"
        );

        assertThat(page4.featuredSelection())
                .extracting(AgePageResponse.FeaturedProduct::title)
                .containsExactly(
                        "Juego Montessori de formas y encajes",
                        "Puzle de madera de animales",
                        "Bicicleta sin pedales básica",
                        "Patinete de 3 ruedas",
                        "Torre de aprendizaje de madera",
                        "Set de vajilla infantil irrompible",
                        "Set de construcción magnético",
                        "Juego de mesa cooperativo",
                        "Kit de manualidades con materiales naturales"
                );
        assertThat(page5.featuredSelection())
                .extracting(AgePageResponse.FeaturedProduct::title)
                .containsExactly(
                        "Puzle de madera de animales",
                        "Bicicleta sin pedales básica",
                        "Torre de aprendizaje de madera",
                        "Set de vajilla infantil irrompible",
                        "Set de construcción magnético",
                        "Juego de mesa cooperativo",
                        "Kit de manualidades con materiales naturales"
                );
        assertThat(page4.quickNavigation())
                .extracting(AgePageResponse.QuickNavItem::label)
                .containsExactlyElementsOf(defaultQuickNavigation);
        assertThat(page5.quickNavigation())
                .extracting(AgePageResponse.QuickNavItem::label)
                .containsExactlyElementsOf(defaultQuickNavigation);
        assertThat(page4.featuredRankings().get(0).href())
                .isEqualTo("/comparativas/mejores-juegos-de-mesa-4-anos/");
        assertThat(page5.featuredRankings().get(0).href())
                .isEqualTo("/comparativas/mejores-juguetes-stem-5-anos/");
        assertThat(movementHref(page4)).isEqualTo("/movimiento/bicicletas-sin-pedales/");
        assertThat(movementHref(page5)).isEqualTo("/movimiento/bicicletas-sin-pedales/");
        assertThat(page4.featuredSelection())
                .filteredOn(product -> product.title().equals("Bicicleta sin pedales básica"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo("/analisis/bici-sin-pedales-basica/");
                    assertThat(product.ctaLabel()).isEqualTo("Ver análisis completo");
                });
        assertThat(page5.featuredSelection())
                .filteredOn(product -> product.title().equals("Bicicleta sin pedales básica"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo("/analisis/bici-sin-pedales-basica/");
                    assertThat(product.ctaLabel()).isEqualTo("Ver análisis completo");
                });
    }

    private static String movementHref(AgePageResponse page) {
        return page.optionsByNeed().stream()
                .filter(group -> group.anchor().equals("#para-moverse"))
                .findFirst()
                .orElseThrow()
                .items()
                .get(0)
                .href();
    }

    @Test
    void doesNotInventCommercialDataInFeaturedSelection() {
        AgePageResponse page = service.getBySlug("4-anos").orElseThrow();

        assertThat(page.featuredSelection()).isNotEmpty();
        assertThat(page.featuredSelection())
                .allSatisfy(product -> assertThat(product.affiliateHref()).isNull());
    }

    @Test
    void exposesOnlyAffiliateLinksValidatedByTheCatalog() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setCredentialId("credential-id");
        properties.setCredentialSecret("credential-secret");
        properties.setPartnerTag("bebesfelices-21");
        properties.setProductAsins(Map.of("bici-chicco-red-bullet", "B012345678"));
        AmazonEnrichedProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> Optional.of(new AmazonProductSnapshot(
                        asin,
                        marketplace,
                        "Título de Amazon",
                        "https://www.amazon.es/dp/" + asin + "?tag=bebesfelices-21",
                        null
                )),
                properties
        );

        AgePageResponse page =
                new AgePageService(catalog).getBySlug("3-anos").orElseThrow();

        assertThat(page.featuredSelection())
                .filteredOn(product -> product.title().equals("Chicco Red Bullet"))
                .extracting(AgePageResponse.FeaturedProduct::affiliateHref)
                .containsExactly("https://www.amazon.es/dp/B012345678?tag=bebesfelices-21");
    }

    @Test
    void breadcrumbsHaveHomeAgeHubAndCurrentAge() {
        AgePageResponse page = service.getBySlug("5-anos").orElseThrow();

        assertThat(page.breadcrumbs()).hasSize(3);
        assertThat(page.breadcrumbs().get(0).label()).isEqualTo("Inicio");
        assertThat(page.breadcrumbs().get(2).label()).isEqualTo("5 años");
    }

    @Test
    void otherAgesExcludesTheCurrentAgeAndListsTheOtherTwo() {
        AgePageResponse page = service.getBySlug("4-anos").orElseThrow();

        assertThat(page.otherAges()).hasSize(2);
        assertThat(page.otherAges()).extracting("ageLabel")
                .containsExactlyInAnyOrder("3 años", "5 años");
    }

    @Test
    void faqAndAffiliationNoticeAreAlwaysPresent() {
        for (String slug : List.of("3-anos", "4-anos", "5-anos")) {
            AgePageResponse page = service.getBySlug(slug).orElseThrow();
            assertThat(page.faq()).isNotEmpty();
            assertThat(page.affiliation().noticeText()).isNotBlank();
        }
    }
}
