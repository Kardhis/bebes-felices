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
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
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
            CollectionPageService.MONTESSORI_SLUG,
            CollectionPageService.PUZZLES_SLUG,
            CollectionPageService.SCOOTERS_SLUG,
            CollectionPageService.TOWERS_SLUG,
            CollectionPageService.TABLEWARE_SLUG,
            CollectionPageService.SUSTAINABLE_SLUG,
            CollectionPageService.GIFTS_3_SLUG
    })
    void returnsPublishedCollections(String slug) throws Exception {
        mockMvc.perform(get("/api/collection-pages/{slug}", slug))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.slug").value(slug))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/3-anos/"))
                .andExpect(jsonPath("$.products", not(empty())))
                .andExpect(jsonPath("$.products[*].affiliateHref", everyItem(nullValue())));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            CollectionPageService.STEM_SLUG,
            CollectionPageService.BALANCE_BIKES_SLUG,
            CollectionPageService.GIFTS_4_SLUG
    })
    void returnsPublishedFourYearCollections(String slug) throws Exception {
        mockMvc.perform(get("/api/collection-pages/{slug}", slug))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/4-anos/"));
    }

    @Test
    void returnsNotFoundForAnUnknownCollection() throws Exception {
        mockMvc.perform(get("/api/collection-pages/no-existe"))
                .andExpect(status().isNotFound());
    }
}
