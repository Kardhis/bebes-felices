package com.bebesfelices.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AgePageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {"3-anos", "4-anos", "5-anos"})
    void returnsTheAgePageForEachValidSlug(String slug) throws Exception {
        mockMvc.perform(get("/api/age-pages/{slug}", slug))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.slug").value(slug))
                .andExpect(jsonPath("$.seo.canonicalUrl").value("https://bebesfelices.es/por-edad/" + slug + "/"))
                .andExpect(jsonPath("$.header.h1", not(emptyOrNullString())))
                .andExpect(jsonPath("$.featuredSelection", not(empty())))
                .andExpect(jsonPath("$.faq", not(empty())))
                .andExpect(jsonPath("$.otherAges.length()").value(2))
                .andExpect(jsonPath("$.affiliation.noticeText", not(emptyOrNullString())));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2-anos", "6-anos", "3-anios"})
    void returnsNotFoundForInvalidSlugs(String slug) throws Exception {
        mockMvc.perform(get("/api/age-pages/{slug}", slug))
                .andExpect(status().isNotFound());
    }

    @Test
    void doesNotInventAffiliateLinksInTheFeaturedSelection() throws Exception {
        mockMvc.perform(get("/api/age-pages/3-anos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.featuredSelection[*].affiliateHref", everyItem(nullValue())));
    }

    @Test
    void contentDiffersBetweenAges() throws Exception {
        String h1For3 = mockMvc.perform(get("/api/age-pages/3-anos"))
                .andReturn().getResponse().getContentAsString();
        String h1For4 = mockMvc.perform(get("/api/age-pages/4-anos"))
                .andReturn().getResponse().getContentAsString();

        org.assertj.core.api.Assertions.assertThat(h1For3).isNotEqualTo(h1For4);
    }
}
