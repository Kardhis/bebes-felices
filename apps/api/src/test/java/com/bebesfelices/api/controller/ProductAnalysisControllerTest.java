package com.bebesfelices.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ProductAnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {
            "juego-montessori-formas",
            "puzle-madera-animales",
            "patinete-3-ruedas",
            "torre-aprendizaje-madera",
            "set-vajilla-infantil",
            "kit-manualidades-natural"
    })
    void returnsPublishedAnalyses(String productId) throws Exception {
        mockMvc.perform(get("/api/product-pages/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.canonicalPath").value("/analisis/" + productId + "/"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/3-anos/"))
                .andExpect(jsonPath("$.affiliateHref").value(nullValue()))
                .andExpect(jsonPath("$.editorialSummary", not(emptyOrNullString())));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "set-construccion-magnetico",
            "bici-sin-pedales-basica"
    })
    void returnsPublishedFourYearAnalyses(String productId) throws Exception {
        mockMvc.perform(get("/api/product-pages/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/4-anos/"));
    }

    @Test
    void returnsNotFoundForTheComparisonSpotlight() throws Exception {
        mockMvc.perform(get("/api/product-pages/bici-chicco-red-bullet"))
                .andExpect(status().isNotFound());
    }
}
