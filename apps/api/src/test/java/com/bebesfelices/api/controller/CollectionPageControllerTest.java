package com.bebesfelices.api.controller;

import com.bebesfelices.api.service.CollectionPageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CollectionPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {
            CollectionPageService.SCOOTERS_SLUG,
            CollectionPageService.TOWERS_SLUG,
            CollectionPageService.TABLEWARE_SLUG,
            CollectionPageService.SUSTAINABLE_3_SLUG,
            CollectionPageService.GIFTS_3_SLUG
    })
    void returnsPublishedCollections(String slug) throws Exception {
        mockMvc.perform(get("/api/collection-pages/{slug}", slug))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.slug").value(slug))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/3-anos/"))
                .andExpect(jsonPath("$.products", not(empty())));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            CollectionPageService.BALANCE_BIKES_SLUG,
            CollectionPageService.GIFTS_4_SLUG,
            CollectionPageService.SUSTAINABLE_4_SLUG
    })
    void returnsPublishedFourYearCollections(String slug) throws Exception {
        mockMvc.perform(get("/api/collection-pages/{slug}", slug))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/4-anos/"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            CollectionPageService.GIFTS_5_SLUG,
            CollectionPageService.SUSTAINABLE_5_SLUG
    })
    void returnsPublishedFiveYearCollections(String slug) throws Exception {
        mockMvc.perform(get("/api/collection-pages/{slug}", slug))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/5-anos/"))
                .andExpect(jsonPath("$.products", not(empty())));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            CollectionPageService.MONTESSORI_SLUG,
            CollectionPageService.PUZZLES_SLUG,
            CollectionPageService.STEM_SLUG,
            CollectionPageService.BOARD_GAMES_SLUG,
            CollectionPageService.SYMBOLIC_PLAY_SLUG,
            CollectionPageService.SENSORY_TOYS_SLUG,
            CollectionPageService.SMALL_WORLDS_SLUG,
            CollectionPageService.MUSICAL_TOYS_SLUG,
            CollectionPageService.CONSTRUCTION_TOYS_SLUG,
            CollectionPageService.ARTS_CRAFTS_SLUG,
            CollectionPageService.EXPERIMENTATION_SLUG,
            CollectionPageService.LITERACY_SLUG,
            CollectionPageService.MATH_LOGIC_SLUG,
            CollectionPageService.COOPERATIVE_SEL_SLUG
    })
    void returnsSixReviewedAmazonProductsForEducationalCollections(String slug) throws Exception {
        mockMvc.perform(get("/api/collection-pages/{slug}", slug))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.products", hasSize(6)))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value(
                        "/juguetes-educativos/?edad=" + expectedEducationalAge(slug)))
                .andExpect(jsonPath("$.products[*].affiliateHref",
                        everyItem(startsWith("https://www.amazon.es/dp/"))));
    }

    @Test
    void returnsNotFoundForAnUnknownCollection() throws Exception {
        mockMvc.perform(get("/api/collection-pages/no-existe"))
                .andExpect(status().isNotFound());
    }

    private static int expectedEducationalAge(String slug) {
        return switch (slug) {
            case CollectionPageService.STEM_SLUG,
                 CollectionPageService.CONSTRUCTION_TOYS_SLUG,
                 CollectionPageService.ARTS_CRAFTS_SLUG,
                 CollectionPageService.EXPERIMENTATION_SLUG -> 4;
            case CollectionPageService.BOARD_GAMES_SLUG,
                 CollectionPageService.LITERACY_SLUG,
                 CollectionPageService.MATH_LOGIC_SLUG,
                 CollectionPageService.COOPERATIVE_SEL_SLUG -> 5;
            default -> 3;
        };
    }
}
