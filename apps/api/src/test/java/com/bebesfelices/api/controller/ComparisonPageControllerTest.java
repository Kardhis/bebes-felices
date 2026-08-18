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
    void returnsNotFoundForAnUnknownComparison() throws Exception {
        mockMvc.perform(get("/api/comparison-pages/no-existe"))
                .andExpect(status().isNotFound());
    }
}
