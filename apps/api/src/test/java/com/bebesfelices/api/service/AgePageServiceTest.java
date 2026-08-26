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
    private static final String STEM_5_HREF =
            "/comparativas/mejores-juguetes-stem-5-anos/";
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
        assertThat(page.quickNavigation()).isEmpty();
        assertThat(page.quickSummary()).isEmpty();

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
                .contains(BALANCE_BIKES_HREF);
        assertThat(page.featuredSelection())
                .allSatisfy(product -> {
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                    assertThat(product.href()).startsWith("/comparativas/");
                });
    }

    @Test
    void threeYearBodyDestinationsStayInsideThePublishedCircuit() {
        AgePageResponse page = service.getBySlug("3-anos").orElseThrow();
        List<String> hrefs = new java.util.ArrayList<>();
        page.optionsByNeed().forEach(group ->
                group.items().forEach(item -> hrefs.add(item.href())));
        page.featuredGuides().forEach(item -> hrefs.add(item.href()));
        page.featuredRankings().forEach(item -> hrefs.add(item.href()));
        page.giftIdeas().forEach(item -> hrefs.add(item.href()));
        page.informativeArticles().forEach(item -> hrefs.add(item.href()));
        page.featuredSelection().forEach(item -> hrefs.add(item.href()));

        List<String> published = List.of(
                "/comparativas/mejores-bicicletas-sin-pedales-3-anos/",
                "/comparativas/mejores-juegos-montessori-3-anos/",
                "/comparativas/mejores-puzles-3-anos/",
                "/comparativas/mejores-patinetes-3-anos/",
                "/comparativas/mejores-torres-aprendizaje-3-anos/",
                "/comparativas/mejores-vajillas-infantiles-3-anos/",
                "/comparativas/mejores-ideas-regalo-3-anos/",
                "/comparativas/mejores-regalos-sostenibles-3-anos/",
                "/guias/como-elegir-juguetes-por-edad/?edad=3",
                "/guias/habilidades-3-anos/",
                "/regalos/ideas-regalo-3-anos/"
        );

        assertThat(hrefs).isNotEmpty();
        assertThat(hrefs).allSatisfy(href -> {
            String path = href.split("#", 2)[0];
            assertThat(published).contains(path);
        });
    }

    @Test
    void fourYearBodyDestinationsStayInsideThePublishedCircuit() {
        AgePageResponse page = service.getBySlug("4-anos").orElseThrow();
        List<String> hrefs = new java.util.ArrayList<>();
        page.optionsByNeed().forEach(group ->
                group.items().forEach(item -> hrefs.add(item.href())));
        page.featuredGuides().forEach(item -> hrefs.add(item.href()));
        page.featuredRankings().forEach(item -> hrefs.add(item.href()));
        page.giftIdeas().forEach(item -> hrefs.add(item.href()));
        page.informativeArticles().forEach(item -> hrefs.add(item.href()));
        page.featuredSelection().forEach(item -> hrefs.add(item.href()));

        List<String> published = List.of(
                "/comparativas/mejores-juegos-de-mesa-4-anos/",
                "/comparativas/mejores-patinetes-4-anos/",
                "/comparativas/mejores-torres-aprendizaje-4-anos/",
                "/comparativas/mejores-vajillas-infantiles-4-anos/",
                "/comparativas/mejores-regalos-sostenibles-4-anos/",
                "/guias/como-elegir-juguetes-por-edad/?edad=4",
                "/guias/habilidades-4-anos/",
                "/juguetes-educativos/juegos-stem/",
                "/movimiento/bicicletas-sin-pedales/",
                "/regalos/ideas-regalo-4-anos/",
                "/analisis/juego-montessori-formas/",
                "/analisis/puzle-madera-animales/",
                "/analisis/bici-sin-pedales-basica/",
                "/analisis/set-construccion-magnetico/"
        );

        assertThat(hrefs).isNotEmpty();
        assertThat(hrefs).allSatisfy(href -> {
            String path = href.split("#", 2)[0];
            assertThat(published).contains(path);
        });
    }

    @Test
    void fiveYearBodyDestinationsStayInsideThePublishedCircuit() {
        AgePageResponse page = service.getBySlug("5-anos").orElseThrow();
        List<String> hrefs = new java.util.ArrayList<>();
        page.optionsByNeed().forEach(group ->
                group.items().forEach(item -> hrefs.add(item.href())));
        page.featuredGuides().forEach(item -> hrefs.add(item.href()));
        page.featuredRankings().forEach(item -> hrefs.add(item.href()));
        page.giftIdeas().forEach(item -> hrefs.add(item.href()));
        page.informativeArticles().forEach(item -> hrefs.add(item.href()));
        page.featuredSelection().forEach(item -> hrefs.add(item.href()));

        List<String> published = List.of(
                STEM_5_HREF,
                "/guias/como-elegir-juguetes-por-edad/?edad=5",
                "/guias/habilidades-5-anos/",
                "/juguetes-educativos/juegos-de-mesa/",
                "/movimiento/bicicletas-sin-pedales/",
                "/movimiento/patinetes/",
                "/autonomia/torres-de-aprendizaje/",
                "/autonomia/vajilla-infantil/",
                "/sostenibles/",
                "/regalos/ideas-regalo-5-anos/",
                "/analisis/puzle-madera-animales/",
                "/analisis/bici-sin-pedales-basica/",
                "/analisis/torre-aprendizaje-madera/",
                "/analisis/set-vajilla-infantil/",
                "/analisis/juego-mesa-cooperativo/",
                "/analisis/kit-manualidades-natural/"
        );

        assertThat(hrefs).isNotEmpty();
        assertThat(hrefs).allSatisfy(href ->
                assertThat(published).contains(href.split("#", 2)[0]));
        assertThat(page.featuredSelection())
                .filteredOn(product -> product.title().equals("Set de construcción magnético"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo(
                            STEM_5_HREF + "#producto-set-construccion-magnetico"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
    }

    @Test
    void keepsFourAndFiveYearOldEditorialContent() {
        AgePageResponse page4 = service.getBySlug("4-anos").orElseThrow();
        AgePageResponse page5 = service.getBySlug("5-anos").orElseThrow();

        assertThat(page4.featuredSelection())
                .extracting(AgePageResponse.FeaturedProduct::title)
                .containsExactly(
                        "Juego Montessori de formas y encajes",
                        "Puzle de madera de animales",
                        "Bicicleta sin pedales básica",
                        "Micro Mini Deluxe LED",
                        "YOLEO Transformer",
                        "Twistshake plato con compartimentos",
                        "Set de construcción magnético",
                        "HABA El Frutal Mini",
                        "Melissa & Doug cuentas de madera"
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
        assertThat(page4.quickNavigation()).isEmpty();
        assertThat(page5.quickNavigation()).isEmpty();
        assertThat(page4.quickSummary()).isEmpty();
        assertThat(page5.quickSummary()).isEmpty();
        assertThat(page4.featuredRankings().get(0).href())
                .isEqualTo("/comparativas/mejores-juegos-de-mesa-4-anos/");
        assertThat(page4.featuredRankings().get(1).href())
                .isEqualTo("/comparativas/mejores-patinetes-4-anos/");
        assertThat(page5.featuredRankings().get(0).href())
                .isEqualTo("/comparativas/mejores-juguetes-stem-5-anos/");
        assertThat(movementHref(page4)).isEqualTo("/movimiento/bicicletas-sin-pedales/");
        assertThat(page4.optionsByNeed().stream()
                        .filter(group -> group.anchor().equals("#para-aprender"))
                        .findFirst()
                        .orElseThrow()
                        .items()
                        .get(1)
                        .href())
                .isEqualTo("/comparativas/mejores-juegos-de-mesa-4-anos/");
        assertThat(page4.optionsByNeed().stream()
                        .filter(group -> group.anchor().equals("#para-moverse"))
                        .findFirst()
                        .orElseThrow()
                        .items()
                        .get(1)
                        .href())
                .isEqualTo("/comparativas/mejores-patinetes-4-anos/");
        assertThat(page4.featuredSelection())
                .filteredOn(product -> product.title().equals("HABA El Frutal Mini"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo(
                            "/comparativas/mejores-juegos-de-mesa-4-anos/#producto-juego-mesa-el-frutal-mini"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
        assertThat(page4.featuredSelection())
                .filteredOn(product -> product.title().equals("Micro Mini Deluxe LED"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo(
                            "/comparativas/mejores-patinetes-4-anos/#producto-patinete-micro-mini-deluxe"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
        assertThat(page4.optionsByNeed().stream()
                        .filter(group -> group.anchor().equals("#para-autonomia"))
                        .findFirst()
                        .orElseThrow()
                        .items())
                .extracting(item -> item.href())
                .containsExactly(
                        "/comparativas/mejores-torres-aprendizaje-4-anos/",
                        "/comparativas/mejores-vajillas-infantiles-4-anos/"
                );
        assertThat(page4.optionsByNeed().stream()
                        .filter(group -> group.anchor().equals("#para-regalar"))
                        .findFirst()
                        .orElseThrow()
                        .items()
                        .get(1)
                        .href())
                .isEqualTo("/comparativas/mejores-regalos-sostenibles-4-anos/");
        assertThat(page4.featuredSelection())
                .filteredOn(product -> product.title().equals("YOLEO Transformer"))
                .allSatisfy(product -> {
                    assertThat(product.href()).isEqualTo(
                            "/comparativas/mejores-torres-aprendizaje-4-anos/#producto-torre-yoleo-transformer"
                    );
                    assertThat(product.ctaLabel()).isEqualTo("Ver comparativa completa");
                });
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
    void createsManualAffiliateLinksForTheThreeYearFeaturedSelection() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setPartnerTag("bebesfelice0c-21");
        properties.setProductAsins(Map.of(
                "juego-montessori-formas", "B00005RF5G",
                "puzle-madera-animales", "B00HWHNNRG",
                "bici-chicco-red-bullet", "B004MW55Z2",
                "patinete-micro-mini-deluxe", "B09PRNX4HX",
                "torre-kleiner-riese", "B0B7RFPP5Z",
                "vajilla-stor-mickey", "B0CZTZ917D",
                "kit-manualidades-natural", "B0015XJUV6"
        ));
        AmazonEnrichedProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> Optional.empty(),
                properties
        );

        AgePageResponse page =
                new AgePageService(catalog).getBySlug("3-anos").orElseThrow();

        assertThat(page.featuredSelection())
                .extracting(AgePageResponse.FeaturedProduct::affiliateHref)
                .containsExactly(
                        "https://www.amazon.es/dp/B00005RF5G?tag=bebesfelice0c-21",
                        "https://www.amazon.es/dp/B00HWHNNRG?tag=bebesfelice0c-21",
                        "https://www.amazon.es/dp/B004MW55Z2?tag=bebesfelice0c-21",
                        "https://www.amazon.es/dp/B09PRNX4HX?tag=bebesfelice0c-21",
                        "https://www.amazon.es/dp/B0B7RFPP5Z?tag=bebesfelice0c-21",
                        "https://www.amazon.es/dp/B0CZTZ917D?tag=bebesfelice0c-21",
                        "https://www.amazon.es/dp/B0015XJUV6?tag=bebesfelice0c-21"
                );
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
            assertThat(page.quickNavigation()).isEmpty();
            assertThat(page.quickSummary()).isEmpty();
        }
    }
}
