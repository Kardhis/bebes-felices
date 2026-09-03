package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.AmazonEnrichedProductCatalog;
import com.bebesfelices.api.catalog.ManualProductCatalog;
import com.bebesfelices.api.catalog.Product;
import com.bebesfelices.api.catalog.ProductCatalog;
import com.bebesfelices.api.catalog.ProductStatus;
import com.bebesfelices.api.catalog.amazon.AmazonCreatorsProperties;
import com.bebesfelices.api.dto.ComparisonPageResponse;
import com.bebesfelices.api.dto.PageStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ComparisonPageServiceTest {

    private static final List<String> PRODUCT_IDS = List.of(
            "bici-chicco-red-bullet",
            "bici-kinderkraft-tove",
            "bici-kinderkraft-fly-plus-2",
            "bici-kinderkraft-goswift",
            "bici-puky-lr-m"
    );

    @Test
    void buildsThePublishedComparisonFromTheFiveRealCatalogProducts() {
        ComparisonPageResponse page = new ComparisonPageService(new ManualProductCatalog())
                .getBySlug(ComparisonPageService.BALANCE_BIKES_SLUG)
                .orElseThrow();

        assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(page.targetAge()).isEqualTo(3);
        assertThat(page.updatedAt()).isEqualTo("2026-08-13");
        assertThat(page.publishedAt()).isEqualTo("2026-08-13");
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactlyElementsOf(PRODUCT_IDS);
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::rank)
                .containsExactly(1, 2, 3, 4, 5);
        assertThat(page.entries()).allSatisfy(entry -> {
            assertThat(entry.title()).isNotBlank();
            assertThat(entry.editorialSummary()).isNotBlank();
            assertThat(entry.pros()).isNotEmpty();
            assertThat(entry.cons()).isNotEmpty();
            assertThat(entry.criteriaNotes()).isNotEmpty();
            assertThat(entry.affiliateHref()).isNull();
        });
        assertThat(page.methodology().criteria()).hasSizeGreaterThanOrEqualTo(4);
        assertThat(page.buyingGuide().sections()).isNotEmpty();
        assertThat(page.faq()).isNotEmpty();
        assertThat(page.breadcrumbs()).hasSize(3);
        assertThat(page.breadcrumbs().get(1).label()).isEqualTo("3 años");
        assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/3-anos/");
        assertThat(page.relatedLinks().get(0).href()).isEqualTo("/por-edad/3-anos/");
        assertThat(page.quickNavigation()).isEmpty();
    }

    @Test
    void returnsEmptyForAnUnknownSlug() {
        ComparisonPageService service = new ComparisonPageService(new ManualProductCatalog());

        assertThat(service.getBySlug("otra-comparativa")).isEmpty();
    }

    @Test
    void createsManualAffiliateLinksWithoutCallingCreatorsApi() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setPartnerTag("bebesfelices-21");
        properties.setProductAsins(Map.of(
                "bici-chicco-red-bullet", "B004MW55Z2",
                "bici-kinderkraft-tove", "B0CF5XRJ6S",
                "bici-kinderkraft-fly-plus-2", "B0CZTVT1DN",
                "bici-kinderkraft-goswift", "B092JTG2YL",
                "bici-puky-lr-m", "B0DJ7DS33P"
        ));
        AtomicInteger creatorsApiCalls = new AtomicInteger();
        ProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> {
                    creatorsApiCalls.incrementAndGet();
                    return Optional.empty();
                },
                properties
        );

        ComparisonPageResponse page = new ComparisonPageService(catalog)
                .getBySlug(ComparisonPageService.BALANCE_BIKES_SLUG)
                .orElseThrow();

        assertThat(creatorsApiCalls).hasValue(0);
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::affiliateHref)
                .containsExactly(
                        "https://www.amazon.es/dp/B004MW55Z2?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B0CF5XRJ6S?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B0CZTVT1DN?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B092JTG2YL?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B0DJ7DS33P?tag=bebesfelices-21"
                );
    }

    @Test
    void filtersInactiveProductsAndKeepsEditorialTitles() {
        ManualProductCatalog manual = new ManualProductCatalog();
        ProductCatalog alteredCatalog = new ProductCatalog() {
            @Override
            public Optional<Product> findById(String id) {
                return manual.findById(id).map(this::alter);
            }

            @Override
            public List<Product> findByIds(List<String> ids) {
                return manual.findByIds(ids).stream().map(this::alter).toList();
            }

            private Product alter(Product product) {
                return new Product(
                        product.id(),
                        product.source(),
                        product.asin(),
                        product.marketplace(),
                        "Título comercial no editorial",
                        product.description(),
                        product.minAge(),
                        product.maxAge(),
                        product.categories(),
                        product.id().equals("bici-kinderkraft-tove")
                                ? ProductStatus.RETIRED
                                : product.status(),
                        product.affiliateLink(),
                        product.lastReviewedAt()
                );
            }
        };

        ComparisonPageResponse page = new ComparisonPageService(alteredCatalog)
                .getBySlug(ComparisonPageService.BALANCE_BIKES_SLUG)
                .orElseThrow();

        assertThat(page.entries()).hasSize(4);
        assertThat(page.entries()).noneMatch(entry ->
                entry.productId().equals("bici-kinderkraft-tove"));
        assertThat(page.entries().get(0).title()).isEqualTo("Chicco Red Bullet");
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::rank)
                .containsExactly(1, 2, 3, 4);
    }

    @Test
    void buildsThePublishedBoardGameComparisonForFourYearOlds() {
        ComparisonPageResponse page = new ComparisonPageService(new ManualProductCatalog())
                .getBySlug(ComparisonPageService.BOARD_GAMES_SLUG)
                .orElseThrow();

        assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(page.targetAge()).isEqualTo(4);
        assertThat(page.updatedAt()).isEqualTo("2026-08-14");
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "lectura-frutalito",
                        "lectura-unicornio-memo",
                        "juego-mesa-animal-sobre-animal",
                        "juego-mesa-dobble-kids",
                        "lectura-three-pigs"
                );
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::rank)
                .containsExactly(1, 2, 3, 4, 5);
        assertThat(page.entries()).allSatisfy(entry -> {
            assertThat(entry.title()).isNotBlank();
            assertThat(entry.pros()).isNotEmpty();
            assertThat(entry.cons()).isNotEmpty();
            assertThat(entry.affiliateHref()).isNull();
        });
        assertThat(page.breadcrumbs().get(1).label()).isEqualTo("4 años");
        assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/4-anos/");
        assertThat(page.relatedLinks().get(0).href()).isEqualTo("/por-edad/4-anos/");
        assertThat(page.quickNavigation()).isEmpty();
    }

    @Test
    void buildsThePublishedScooterComparisonForFourYearOlds() {
        ComparisonPageResponse page = new ComparisonPageService(new ManualProductCatalog())
                .getBySlug(ComparisonPageService.SCOOTERS_SLUG)
                .orElseThrow();

        assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(page.targetAge()).isEqualTo(4);
        assertThat(page.updatedAt()).isEqualTo("2026-08-14");
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "patinete-micro-mini-deluxe",
                        "patinete-molto-maxi",
                        "patinete-globber-junior-foldable",
                        "patinete-globber-master-lights",
                        "triciclo-chicco-u-go"
                );
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::rank)
                .containsExactly(1, 2, 3, 4, 5);
        assertThat(page.entries()).allSatisfy(entry -> {
            assertThat(entry.title()).isNotBlank();
            assertThat(entry.pros()).isNotEmpty();
            assertThat(entry.cons()).isNotEmpty();
            assertThat(entry.affiliateHref()).isNull();
        });
        assertThat(page.header().h1()).isEqualTo("Mejores patinetes y triciclos para 4 años");
        assertThat(page.breadcrumbs().get(1).label()).isEqualTo("4 años");
        assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/4-anos/");
        assertThat(page.relatedLinks().get(0).href()).isEqualTo("/por-edad/4-anos/");
        assertThat(page.quickNavigation()).isEmpty();
    }

    @Test
    void buildsThePublishedFourYearAutonomyAndSustainableComparisons() {
        ComparisonPageService service = new ComparisonPageService(new ManualProductCatalog());

        ComparisonPageResponse towers = service.getBySlug(ComparisonPageService.TOWERS_SLUG).orElseThrow();
        ComparisonPageResponse tableware = service.getBySlug(ComparisonPageService.TABLEWARE_SLUG).orElseThrow();
        ComparisonPageResponse sustainable = service.getBySlug(ComparisonPageService.SUSTAINABLE_SLUG).orElseThrow();

        assertThat(towers.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "torre-yoleo-transformer",
                        "torre-hauck-learn-n-explore",
                        "torre-costway-plegable",
                        "torre-bey-co",
                        "torre-maxi-cosi-toucan"
                );
        assertThat(tableware.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "vajilla-twistshake-dividido",
                        "vajilla-stor-mickey",
                        "vaso-munchkin-miracle-360",
                        "vajilla-fun-house",
                        "cuenco-twistshake-tapa"
                );
        assertThat(sustainable.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "simbolico-janod-veterinario",
                        "plantoys-ata-zapato",
                        "haba-puzles-cuatro-estaciones",
                        "small-foot-grua",
                        "green-toys-construccion"
                );
        assertThat(List.of(towers, tableware, sustainable)).allSatisfy(page -> {
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.targetAge()).isEqualTo(4);
            assertThat(page.entries()).hasSize(5);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/4-anos/");
            assertThat(page.entries()).allSatisfy(entry -> assertThat(entry.affiliateHref()).isNull());
            assertThat(page.quickNavigation()).isEmpty();
        });
    }

    @Test
    void buildsThePublishedThreeYearNeedComparisons() {
        ComparisonPageService service = new ComparisonPageService(new ManualProductCatalog());

        ComparisonPageResponse montessori = service.getBySlug(ComparisonPageService.MONTESSORI_3_SLUG).orElseThrow();
        ComparisonPageResponse puzzles = service.getBySlug(ComparisonPageService.PUZZLES_3_SLUG).orElseThrow();
        ComparisonPageResponse scooters = service.getBySlug(ComparisonPageService.SCOOTERS_3_SLUG).orElseThrow();
        ComparisonPageResponse towers = service.getBySlug(ComparisonPageService.TOWERS_3_SLUG).orElseThrow();
        ComparisonPageResponse tableware = service.getBySlug(ComparisonPageService.TABLEWARE_3_SLUG).orElseThrow();
        ComparisonPageResponse gifts = service.getBySlug(ComparisonPageService.GIFTS_3_SLUG).orElseThrow();
        ComparisonPageResponse sustainable = service.getBySlug(ComparisonPageService.SUSTAINABLE_3_SLUG).orElseThrow();
        ComparisonPageResponse durable = service.getBySlug(ComparisonPageService.DURABLE_3_SLUG).orElseThrow();

        assertThat(montessori.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "montessori-janod-animales",
                        "montessori-melissa-tres-puzzles",
                        "montessori-janod-ballenas",
                        "montessori-janod-tropik",
                        "puzle-melissa-granja-peg"
                );
        assertThat(puzzles.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "puzle-madera-animales",
                        "puzle-melissa-mascotas",
                        "puzle-educa-selva",
                        "haba-puzles-cuatro-estaciones",
                        "puzle-educa-disney-madera"
                );
        assertThat(scooters.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "patinete-yvolution-y-glider",
                        "patinete-colorbaby-eezi-mini",
                        "patinete-globber-primo-foldable",
                        "patinete-micro-mini-3en1",
                        "triciclo-chicco-u-go"
                );
        assertThat(towers.entries().get(0).productId()).isEqualTo("torre-costway-plegable");
        assertThat(tableware.entries().get(0).productId()).isEqualTo("vajilla-stor-mickey");
        assertThat(gifts.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "juego-montessori-formas",
                        "puzle-madera-animales",
                        "bici-chicco-red-bullet",
                        "torre-yoleo-transformer",
                        "kit-manualidades-natural"
                );
        assertThat(sustainable.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "cuentas-melissa-doug",
                        "small-foot-grua",
                        "green-toys-construccion",
                        "haba-puzles-cuatro-estaciones",
                        "plantoys-ata-zapato"
                );
        assertThat(durable.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "puzle-madera-animales",
                        "puzle-melissa-mascotas",
                        "puzle-educa-selva",
                        "puzle-educa-disney-madera",
                        "simbolico-theo-klein-miele"
                );
        List<String> sosteniblesNeedProductIds = Stream.of(
                        service.getBySlug(ComparisonPageService.SUSTAINABLE_3_SLUG).orElseThrow(),
                        service.getBySlug(ComparisonPageService.DURABLE_3_SLUG).orElseThrow(),
                        service.getBySlug(ComparisonPageService.ARTS_NATURAL_3_SLUG).orElseThrow(),
                        service.getBySlug(ComparisonPageService.MONTESSORI_WOOD_3_SLUG).orElseThrow()
                )
                .flatMap(page -> page.entries().stream().map(ComparisonPageResponse.Entry::productId))
                .toList();
        assertThat(sosteniblesNeedProductIds).doesNotHaveDuplicates().hasSize(20);
        assertThat(List.of(montessori, puzzles, scooters, towers, tableware, gifts, sustainable, durable))
                .allSatisfy(page -> {
                    assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
                    assertThat(page.targetAge()).isEqualTo(3);
                    assertThat(page.updatedAt()).isEqualTo("2026-08-26");
                    assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/3-anos/");
                    assertThat(page.relatedLinks().get(0).href()).isEqualTo("/por-edad/3-anos/");
                    assertThat(page.quickNavigation()).isEmpty();
                    assertThat(page.entries()).allSatisfy(entry -> {
                        assertThat(entry.pros()).isNotEmpty();
                        assertThat(entry.cons()).isNotEmpty();
                        assertThat(entry.criteriaNotes()).isNotEmpty();
                        assertThat(entry.affiliateHref()).isNull();
                    });
                });
        assertThat(scooters.entries()).hasSize(5);
        assertThat(montessori.header().h1()).isEqualTo("Mejores juegos Montessori de formas y encajes para 3 años");
        assertThat(scooters.header().h1()).isEqualTo("Mejores patinetes de 3 ruedas para 3 años");
        assertThat(durable.header().h1()).isEqualTo("Mejores regalos duraderos para 3 años");
        assertThat(durable.relatedLinks()).extracting(link -> link.href())
                .contains("/por-edad/3-anos/", "/comparativas/" + ComparisonPageService.SUSTAINABLE_3_SLUG + "/");
    }

    @Test
    void buildsThePublishedThreeYearSecondaryNeedComparisons() {
        ComparisonPageService service = new ComparisonPageService(new ManualProductCatalog());

        ComparisonPageResponse arts = service.getBySlug(ComparisonPageService.ARTS_NATURAL_3_SLUG).orElseThrow();
        ComparisonPageResponse wood = service.getBySlug(ComparisonPageService.MONTESSORI_WOOD_3_SLUG).orElseThrow();
        ComparisonPageResponse symbolic = service.getBySlug(ComparisonPageService.SYMBOLIC_3_SLUG).orElseThrow();
        ComparisonPageResponse sensory = service.getBySlug(ComparisonPageService.SENSORY_3_SLUG).orElseThrow();
        ComparisonPageResponse balanceGuide = service.getBySlug(ComparisonPageService.BALANCE_GUIDE_3_SLUG).orElseThrow();
        ComparisonPageResponse scootersTrikes = service.getBySlug(ComparisonPageService.SCOOTERS_TRIKES_3_SLUG).orElseThrow();
        ComparisonPageResponse pikler = service.getBySlug(ComparisonPageService.PIKLER_3_SLUG).orElseThrow();
        ComparisonPageResponse rideOn = service.getBySlug(ComparisonPageService.RIDE_ON_3_SLUG).orElseThrow();
        ComparisonPageResponse cutlery = service.getBySlug(ComparisonPageService.CUTLERY_3_SLUG).orElseThrow();
        ComparisonPageResponse dressing = service.getBySlug(ComparisonPageService.DRESSING_3_SLUG).orElseThrow();
        ComparisonPageResponse towersKitchen = service.getBySlug(ComparisonPageService.TOWERS_KITCHEN_3_SLUG).orElseThrow();
        ComparisonPageResponse tablewareDaily = service.getBySlug(ComparisonPageService.TABLEWARE_DAILY_3_SLUG).orElseThrow();
        ComparisonPageResponse giftSelection = service.getBySlug(ComparisonPageService.GIFT_SELECTION_3_SLUG).orElseThrow();
        ComparisonPageResponse chooseGift = service.getBySlug(ComparisonPageService.CHOOSE_GIFT_3_SLUG).orElseThrow();

        assertThat(arts.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "arte-ses-eco-mega-7",
                        "arte-jovi-pintura-dedos-6",
                        "arte-crayola-effects",
                        "arte-jovi-plastilina-vegetal-12",
                        "arte-crayola-paw-patrol"
                );
        assertThat(wood.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "juego-montessori-formas",
                        "montessori-goula-baby-shapes",
                        "simbolico-sundaymot-33",
                        "puzle-melissa-granja-peg",
                        "lectura-three-pigs"
                );
        assertThat(symbolic.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "simbolico-theo-klein-miele",
                        "simbolico-kidkraft-vintage",
                        "simbolico-small-foot-compacta",
                        "simbolico-janod-macaron",
                        "simbolico-janod-veterinario"
                );
        assertThat(sensory.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "sensorial-emotion-bottles",
                        "sensorial-playfoam",
                        "sensorial-fidget-tubes",
                        "sensorial-scoops",
                        "sensorial-pinzas-jumbo"
                );
        assertThat(balanceGuide.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "bici-chicco-red-bullet",
                        "bici-kinderkraft-tove",
                        "bici-kinderkraft-fly-plus-2",
                        "bici-kinderkraft-goswift",
                        "bici-puky-lr-m"
                );
        assertThat(scootersTrikes.entries()).hasSize(5);
        assertThat(pikler.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "trepar-mamoi-triangulo-blanco",
                        "trepar-aiyaplay-3en1",
                        "trepar-little-tikes-gimnasio",
                        "trepar-smoby-xs",
                        "trepar-costway-7en1"
                );
        assertThat(rideOn.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "corre-injusa-winner-repsol",
                        "corre-injusa-tundra-tornado",
                        "corre-feber-dream",
                        "corre-molto-cross-premium",
                        "corre-little-tikes-cozy-coupe"
                );
        assertThat(cutlery.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "cubiertos-twistshake-acero",
                        "cubiertos-mam-aprendizaje",
                        "cubiertos-wmf-animales",
                        "cubiertos-exzact-safari",
                        "cubiertos-lehoo-vehiculos"
                );
        assertThat(dressing.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "vestir-melissa-habilidades",
                        "vestir-melissa-cordones",
                        "vestir-small-foot-cubo",
                        "vestir-melissa-disfraces",
                        "vestir-melissa-pestillos"
                );
        assertThat(towersKitchen.entries().get(0).productId()).isEqualTo("torre-costway-plegable");
        assertThat(tablewareDaily.entries().get(0).productId()).isEqualTo("vajilla-stor-mickey");
        assertThat(giftSelection.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "puzle-madera-animales",
                        "patinete-micro-mini-deluxe",
                        "vajilla-stor-mickey",
                        "haba-puzles-cuatro-estaciones",
                        "cuentas-melissa-doug"
                );
        assertThat(chooseGift.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "juego-montessori-formas",
                        "patinete-micro-mini-deluxe",
                        "vajilla-stor-mickey",
                        "kit-manualidades-natural",
                        "small-foot-grua"
                );
        assertThat(List.of(
                arts, wood, symbolic, sensory, balanceGuide, scootersTrikes,
                pikler, rideOn, cutlery, dressing, towersKitchen, tablewareDaily, giftSelection, chooseGift
        )).allSatisfy(page -> {
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.targetAge()).isEqualTo(3);
            assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/3-anos/");
            assertThat(page.relatedLinks().get(0).href()).isEqualTo("/por-edad/3-anos/");
            assertThat(page.quickSummary()).isNotEmpty();
            assertThat(page.methodology().criteria()).isNotEmpty();
            assertThat(page.buyingGuide().sections()).isNotEmpty();
            assertThat(page.faq()).isNotEmpty();
        });
        assertThat(arts.header().h1()).isEqualTo("Arte y manualidades con materiales naturales para 3 años");
        assertThat(chooseGift.header().h1()).isEqualTo("Cómo elegir el regalo según la edad a los 3 años");
    }

    @Test
    void buildsThePublishedFourAndFiveYearNeedComparisons() {
        ComparisonPageService service = new ComparisonPageService(new ManualProductCatalog());

        ComparisonPageResponse durable4 = service.getBySlug(ComparisonPageService.DURABLE_4_SLUG).orElseThrow();
        ComparisonPageResponse montessori4 = service.getBySlug(ComparisonPageService.MONTESSORI_4_SLUG).orElseThrow();
        ComparisonPageResponse stem4 = service.getBySlug(ComparisonPageService.STEM_4_SLUG).orElseThrow();
        ComparisonPageResponse balance4 = service.getBySlug(ComparisonPageService.BALANCE_BIKES_4_SLUG).orElseThrow();
        ComparisonPageResponse gifts4 = service.getBySlug(ComparisonPageService.GIFTS_4_SLUG).orElseThrow();
        ComparisonPageResponse puzzles4 = service.getBySlug(ComparisonPageService.PUZZLES_4_SLUG).orElseThrow();

        ComparisonPageResponse durable5 = service.getBySlug(ComparisonPageService.DURABLE_5_SLUG).orElseThrow();
        ComparisonPageResponse montessori5 = service.getBySlug(ComparisonPageService.MONTESSORI_5_SLUG).orElseThrow();
        ComparisonPageResponse board5 = service.getBySlug(ComparisonPageService.BOARD_GAMES_5_SLUG).orElseThrow();
        ComparisonPageResponse balance5 = service.getBySlug(ComparisonPageService.BALANCE_BIKES_5_SLUG).orElseThrow();
        ComparisonPageResponse gifts5 = service.getBySlug(ComparisonPageService.GIFTS_5_SLUG).orElseThrow();

        assertThat(durable4.targetAge()).isEqualTo(4);
        assertThat(durable4.updatedAt()).isEqualTo("2026-08-14");
        assertThat(durable4.breadcrumbs().get(1).href()).isEqualTo("/por-edad/4-anos/");
        assertThat(durable4.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "small-foot-grua",
                        "green-toys-construccion",
                        "plantoys-ata-zapato",
                        "haba-puzles-cuatro-estaciones",
                        "simbolico-janod-veterinario"
                );
        ComparisonPageResponse artsNatural4 = service.getBySlug(ComparisonPageService.ARTS_NATURAL_4_SLUG).orElseThrow();
        assertThat(artsNatural4.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "kit-manualidades-natural",
                        "arte-crayola-tempera-6",
                        "arte-jovi-pintura-dedos-6",
                        "arte-jovi-plastilina-vegetal-12",
                        "arte-crayola-paw-patrol"
                );
        ComparisonPageResponse wood4 = service.getBySlug(ComparisonPageService.MONTESSORI_WOOD_4_SLUG).orElseThrow();
        assertThat(wood4.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "montessori-janod-animales",
                        "montessori-melissa-tres-puzzles",
                        "simbolico-sundaymot-33",
                        "plantoys-ata-zapato",
                        "lectura-three-pigs"
                );
        assertThat(wood4.entries()).extracting(ComparisonPageResponse.Entry::title)
                .doesNotContain(
                        "Melissa & Doug cuentas de madera",
                        "Goula Baby Shapes",
                        "Janod clasificar ballenas por colores",
                        "Melissa & Doug Arca de Noé clasificadora"
                );
        assertThat(montessori4.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "montessori-janod-animales",
                        "montessori-melissa-tres-puzzles",
                        "haba-puzles-cuatro-estaciones",
                        "vestir-melissa-habilidades",
                        "plantoys-ata-zapato"
                );
        assertThat(montessori4.entries()).extracting(ComparisonPageResponse.Entry::title)
                .doesNotContain(
                        "Melissa & Doug cubo de formas",
                        "Goula Baby Shapes",
                        "Melissa & Doug puzzle de formas geométricas",
                        "Melissa & Doug Arca de Noé clasificadora",
                        "Janod clasificar ballenas por colores",
                        "Melissa & Doug cuentas de madera"
                );
        assertThat(puzzles4.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "puzle-ravensburger-toy-story-4en1",
                        "puzle-ravensburger-gruffalo-suelo-24",
                        "puzle-clementoni-progresivo-4en1",
                        "puzle-educa-bluey-madera-16",
                        "puzle-clementoni-dinosaurios-20"
                );
        assertThat(puzzles4.entries()).extracting(ComparisonPageResponse.Entry::title)
                .doesNotContain(
                        "Melissa & Doug pack de 3 puzzles de encaje",
                        "Melissa & Doug puzle de mascotas",
                        "HABA Puzzles Las Cuatro Estaciones",
                        "Educa Disney Animals, 2 puzles de madera",
                        "Educa My First animales de la selva",
                        "Janod 4 puzles evolutivos dinosaurios",
                        "Ravensburger Elmer, rompecabezas de 16 piezas",
                        "Educa Cars, 2 puzles de madera de 16 piezas"
                );
        List<String> puzzles4ProductIds = puzzles4.entries().stream()
                .map(ComparisonPageResponse.Entry::productId)
                .toList();
        assertThat(puzzles4ProductIds).doesNotHaveDuplicates();
        assertThat(puzzles4ProductIds).doesNotContain(
                "montessori-melissa-tres-puzzles",
                "puzle-melissa-mascotas",
                "haba-puzles-cuatro-estaciones",
                "puzle-educa-disney-madera",
                "puzle-janod-evolutivos-granja"
        );
        assertThat(montessori4.entries()).hasSize(5);
        assertThat(stem4.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "stem-geomag-rainbow",
                        "stem-gears-beginners",
                        "juego-mesa-animal-sobre-animal",
                        "stem-gravitrax-junior",
                        "juego-mesa-dobble-kids"
                );
        assertThat(stem4.entries()).extracting(ComparisonPageResponse.Entry::title)
                .doesNotContain(
                        "Set de construcción magnético",
                        "Small Foot grúa de construcción",
                        "HABA Puzzles Las Cuatro Estaciones"
                );
        assertThat(balance4.entries()).hasSize(5);
        assertThat(gifts4.entries()).hasSize(5);

        assertThat(durable5.targetAge()).isEqualTo(5);
        assertThat(durable5.updatedAt()).isEqualTo("2026-08-17");
        assertThat(durable5.breadcrumbs().get(1).href()).isEqualTo("/por-edad/5-anos/");
        assertThat(montessori5.entries()).hasSize(5);
        assertThat(board5.entries()).hasSize(5);
        assertThat(balance5.entries()).hasSize(5);
        assertThat(gifts5.entries()).hasSize(5);

        assertThat(List.of(durable4, montessori4, stem4, balance4, gifts4, puzzles4, durable5, montessori5, board5, balance5, gifts5))
                .allSatisfy(page -> {
                    assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
                    assertThat(page.entries()).allSatisfy(entry -> {
                        assertThat(entry.pros()).isNotEmpty();
                        assertThat(entry.cons()).isNotEmpty();
                        assertThat(entry.affiliateHref()).isNull();
                    });
                    assertThat(page.quickNavigation()).isEmpty();
                });
        assertThat(durable4.header().h1()).isEqualTo("Mejores regalos duraderos para 4 años");
        assertThat(durable5.header().h1()).isEqualTo("Mejores regalos duraderos para 5 años");
    }

    @Test
    void buildsThePublishedStemComparisonForFiveYearOlds() {
        ComparisonPageResponse page = new ComparisonPageService(new ManualProductCatalog())
                .getBySlug(ComparisonPageService.STEM_5_SLUG)
                .orElseThrow();

        assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(page.targetAge()).isEqualTo(5);
        assertThat(page.publishedAt()).isEqualTo("2026-08-17");
        assertThat(page.updatedAt()).isEqualTo("2026-08-17");
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::productId)
                .containsExactly(
                        "set-construccion-magnetico",
                        "small-foot-grua",
                        "juego-mesa-animal-sobre-animal",
                        "haba-puzles-cuatro-estaciones",
                        "juego-mesa-dobble-kids"
                );
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::rank)
                .containsExactly(1, 2, 3, 4, 5);
        assertThat(page.entries()).allSatisfy(entry -> {
            assertThat(entry.editorialSummary()).isNotBlank();
            assertThat(entry.criteriaNotes()).isNotEmpty();
            assertThat(entry.affiliateHref()).isNull();
        });
        assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/por-edad/5-anos/");
        assertThat(page.quickNavigation()).isEmpty();
        assertThat(page.relatedLinks()).extracting(link -> link.href())
                .contains(
                        "/por-edad/5-anos/",
                        "/regalos/ideas-regalo-5-anos/",
                        "/guias/habilidades-5-anos/"
                );
    }

    @Test
    void createsValidatedManualAffiliateLinksForFiveYearStemProducts() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setPartnerTag("bebesfelices-21");
        properties.setProductAsins(Map.of(
                "small-foot-grua", "B07MVR126C",
                "juego-mesa-animal-sobre-animal", "B00D6J9SJQ",
                "haba-puzles-cuatro-estaciones", "B01CSUXO2U",
                "juego-mesa-dobble-kids", "B00OM7VIC6"
        ));
        ProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> Optional.empty(),
                properties
        );

        ComparisonPageResponse page = new ComparisonPageService(catalog)
                .getBySlug(ComparisonPageService.STEM_5_SLUG)
                .orElseThrow();

        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::affiliateHref)
                .containsExactly(
                        null,
                        "https://www.amazon.es/dp/B07MVR126C?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B00D6J9SJQ?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B01CSUXO2U?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B00OM7VIC6?tag=bebesfelices-21"
                );
    }

    @Test
    void createsManualAffiliateLinksForBoardGamesWithoutCallingCreatorsApi() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setPartnerTag("bebesfelices-21");
        properties.setProductAsins(Map.of(
                "lectura-frutalito", "B0088MES78",
                "lectura-unicornio-memo", "B086FCQT6M",
                "juego-mesa-animal-sobre-animal", "B00D6J9SJQ",
                "juego-mesa-dobble-kids", "B00OM7VIC6",
                "lectura-three-pigs", "B07B37TT7F"
        ));
        AtomicInteger creatorsApiCalls = new AtomicInteger();
        ProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> {
                    creatorsApiCalls.incrementAndGet();
                    return Optional.empty();
                },
                properties
        );

        ComparisonPageResponse page = new ComparisonPageService(catalog)
                .getBySlug(ComparisonPageService.BOARD_GAMES_SLUG)
                .orElseThrow();

        assertThat(creatorsApiCalls).hasValue(0);
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::affiliateHref)
                .containsExactly(
                        "https://www.amazon.es/dp/B0088MES78?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B086FCQT6M?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B00D6J9SJQ?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B00OM7VIC6?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B07B37TT7F?tag=bebesfelices-21"
                );
    }

    @Test
    void createsManualAffiliateLinksForScootersWithoutCallingCreatorsApi() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setPartnerTag("bebesfelices-21");
        properties.setProductAsins(Map.of(
                "patinete-micro-mini-deluxe", "B0B82TSPP8",
                "patinete-molto-maxi", "B0D45VJLR8",
                "patinete-globber-junior-foldable", "B0BYSX61WD",
                "patinete-globber-master-lights", "B08G19X6GK",
                "patinete-micro-mini-3en1", "B07RM5Z2LY",
                "triciclo-chicco-u-go", "B00URLWKYG"
        ));
        AtomicInteger creatorsApiCalls = new AtomicInteger();
        ProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> {
                    creatorsApiCalls.incrementAndGet();
                    return Optional.empty();
                },
                properties
        );

        ComparisonPageResponse page = new ComparisonPageService(catalog)
                .getBySlug(ComparisonPageService.SCOOTERS_SLUG)
                .orElseThrow();

        assertThat(creatorsApiCalls).hasValue(0);
        assertThat(page.entries()).extracting(ComparisonPageResponse.Entry::affiliateHref)
                .containsExactly(
                        "https://www.amazon.es/dp/B0B82TSPP8?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B0D45VJLR8?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B0BYSX61WD?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B08G19X6GK?tag=bebesfelices-21",
                        "https://www.amazon.es/dp/B00URLWKYG?tag=bebesfelices-21"
                );
    }
}
