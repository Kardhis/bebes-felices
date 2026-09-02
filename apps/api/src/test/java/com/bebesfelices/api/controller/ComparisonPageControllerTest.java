package com.bebesfelices.api.controller;

import com.bebesfelices.api.service.ComparisonPageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ComparisonPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsThePublishedBalanceBikeComparison() throws Exception {
        mockMvc.perform(get(
                        "/api/comparison-pages/{slug}",
                        ComparisonPageService.BALANCE_BIKES_SLUG
                ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.slug")
                        .value(ComparisonPageService.BALANCE_BIKES_SLUG))
                .andExpect(jsonPath("$.targetAge").value(3))
                .andExpect(jsonPath("$.entries.length()").value(5))
                .andExpect(jsonPath("$.entries[0].productId")
                        .value("bici-chicco-red-bullet"))
                .andExpect(jsonPath("$.entries[4].productId")
                        .value("bici-puky-lr-m"))
                .andExpect(jsonPath("$.methodology.criteria[0].name",
                        not(isEmptyOrNullString())))
                .andExpect(jsonPath("$.entries[*].affiliateHref",
                        everyItem(startsWith("https://www.amazon.es/dp/"))))
                .andExpect(jsonPath("$.breadcrumbs[1].label").value("3 años"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/3-anos/"))
                .andExpect(jsonPath("$.relatedLinks[0].href").value("/por-edad/3-anos/"));
    }

    @Test
    void returnsThePublishedBoardGameComparison() throws Exception {
        mockMvc.perform(get(
                        "/api/comparison-pages/{slug}",
                        ComparisonPageService.BOARD_GAMES_SLUG
                ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.slug")
                        .value(ComparisonPageService.BOARD_GAMES_SLUG))
                .andExpect(jsonPath("$.targetAge").value(4))
                .andExpect(jsonPath("$.entries.length()").value(5))
                .andExpect(jsonPath("$.entries[0].productId")
                        .value("juego-mesa-el-frutal-mini"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/4-anos/"))
                .andExpect(jsonPath("$.relatedLinks[0].href").value("/por-edad/4-anos/"));
    }

    @Test
    void returnsThePublishedScooterComparison() throws Exception {
        mockMvc.perform(get(
                        "/api/comparison-pages/{slug}",
                        ComparisonPageService.SCOOTERS_SLUG
                ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.slug")
                        .value(ComparisonPageService.SCOOTERS_SLUG))
                .andExpect(jsonPath("$.targetAge").value(4))
                .andExpect(jsonPath("$.entries.length()").value(5))
                .andExpect(jsonPath("$.entries[0].productId")
                        .value("patinete-micro-mini-deluxe"))
                .andExpect(jsonPath("$.entries[4].productId")
                        .value("triciclo-chicco-u-go"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/4-anos/"))
                .andExpect(jsonPath("$.relatedLinks[0].href").value("/por-edad/4-anos/"));
    }

    @Test
    void returnsThePublishedThreeYearNeedComparisons() throws Exception {
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.MONTESSORI_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.targetAge").value(3))
                .andExpect(jsonPath("$.entries[0].productId").value("montessori-janod-animales"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.PUZZLES_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("puzle-madera-animales"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.SCOOTERS_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries.length()").value(5))
                .andExpect(jsonPath("$.entries[0].productId").value("patinete-yvolution-y-glider"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.TOWERS_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("torre-costway-plegable"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.TABLEWARE_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("vajilla-stor-mickey"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.GIFTS_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("juego-montessori-formas"))
                .andExpect(jsonPath("$.entries[3].productId").value("torre-yoleo-transformer"))
                .andExpect(jsonPath("$.entries[3].title").value("YOLEO Transformer"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.SUSTAINABLE_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("cuentas-melissa-doug"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.DURABLE_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.h1").value("Mejores regalos duraderos para 3 años"))
                .andExpect(jsonPath("$.entries[0].productId").value("puzle-madera-animales"))
                .andExpect(jsonPath("$.entries.length()").value(5));
    }

    @Test
    void returnsThePublishedThreeYearSecondaryNeedComparisons() throws Exception {
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.ARTS_NATURAL_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("arte-ses-eco-mega-7"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.MONTESSORI_WOOD_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("juego-montessori-formas"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.SYMBOLIC_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("simbolico-theo-klein-miele"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.SENSORY_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("sensorial-emotion-bottles"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.BALANCE_GUIDE_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("bici-chicco-red-bullet"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.SCOOTERS_TRIKES_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries.length()").value(5));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.PIKLER_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries.length()").value(5))
                .andExpect(jsonPath("$.entries[0].productId").value("trepar-mamoi-triangulo-blanco"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.RIDE_ON_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries.length()").value(5))
                .andExpect(jsonPath("$.entries[0].productId").value("corre-injusa-africa-twin"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.CUTLERY_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries.length()").value(5))
                .andExpect(jsonPath("$.entries[0].productId").value("cubiertos-twistshake-acero"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.DRESSING_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries.length()").value(5))
                .andExpect(jsonPath("$.entries[0].productId").value("vestir-melissa-habilidades"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.TOWERS_KITCHEN_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("torre-costway-plegable"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.TABLEWARE_DAILY_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("vajilla-stor-mickey"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.GIFT_SELECTION_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("puzle-madera-animales"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.CHOOSE_GIFT_3_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.h1").value("Cómo elegir el regalo según la edad a los 3 años"))
                .andExpect(jsonPath("$.entries[0].productId").value("juego-montessori-formas"));
    }

    @Test
    void returnsThePublishedFourYearAutonomyComparisons() throws Exception {
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.TOWERS_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("torre-yoleo-transformer"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.TABLEWARE_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("vajilla-twistshake-dividido"));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.SUSTAINABLE_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entries[0].productId").value("cuentas-melissa-doug"));
    }

    @Test
    void returnsThePublishedFiveYearStemComparison() throws Exception {
        mockMvc.perform(get(
                        "/api/comparison-pages/{slug}",
                        ComparisonPageService.STEM_5_SLUG
                ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.targetAge").value(5))
                .andExpect(jsonPath("$.entries.length()").value(5))
                .andExpect(jsonPath("$.entries[0].productId")
                        .value("set-construccion-magnetico"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/5-anos/"));
    }

    @Test
    void returnsThePublishedFourAndFiveYearNeedComparisons() throws Exception {
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.DURABLE_4_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.targetAge").value(4))
                .andExpect(jsonPath("$.entries.length()").value(5));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.STEM_4_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.targetAge").value(4));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.BALANCE_BIKES_4_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.targetAge").value(4));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.DURABLE_5_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.targetAge").value(5));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.BOARD_GAMES_5_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.targetAge").value(5));
        mockMvc.perform(get("/api/comparison-pages/{slug}", ComparisonPageService.BALANCE_BIKES_5_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.targetAge").value(5));
    }

    @Test
    void returnsNotFoundForAnUnknownComparison() throws Exception {
        mockMvc.perform(get("/api/comparison-pages/no-existe"))
                .andExpect(status().isNotFound());
    }
}
