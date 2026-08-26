package com.bebesfelices.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test de regresión tras extraer los tipos compartidos ({@code Seo},
 * {@code LinkItem}, {@code AgeLink}...) a {@code dto.shared} para
 * reutilizarlos también en {@link AgePageController}.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsTheHomeContract() throws Exception {
        mockMvc.perform(get("/api/home"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seo.canonicalUrl").value("https://bebesfelices.es/"))
                .andExpect(jsonPath("$.hero.h1", not(emptyOrNullString())))
                .andExpect(jsonPath("$.ageNavigation.length()").value(3))
                .andExpect(jsonPath("$.ageNavigation[0].href").value("/por-edad/3-anos/"))
                .andExpect(jsonPath("$.mainCategories.length()").value(5))
                .andExpect(jsonPath("$.mainCategories[0].href").value("/sostenibles/"))
                .andExpect(jsonPath("$.recentComparisons.length()").value(3))
                .andExpect(jsonPath("$.affiliation.noticeText", not(emptyOrNullString())))
                .andExpect(jsonPath("$.trustAuthority.analysisCriteria.length()").value(4))
                .andExpect(jsonPath("$.legalLinks.length()").value(5))
                .andExpect(jsonPath("$.featuredGuides[1].title").value("Ideas de regalo por edad"))
                .andExpect(jsonPath("$.featuredGuides[1].href").value("/regalos/"))
                .andExpect(jsonPath("$.updatedAt").value("2026-08-17"));
    }
}
