package com.bebesfelices.api.controller;

import com.bebesfelices.api.service.ComparisonPageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
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
                        everyItem(nullValue())))
                .andExpect(jsonPath("$.breadcrumbs[1].label").value("3 años"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/3-anos/"))
                .andExpect(jsonPath("$.relatedLinks[0].href").value("/por-edad/3-anos/"));
    }

    @Test
    void returnsNotFoundForAnUnknownComparison() throws Exception {
        mockMvc.perform(get("/api/comparison-pages/no-existe"))
                .andExpect(status().isNotFound());
    }
}
