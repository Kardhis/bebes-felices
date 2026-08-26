package com.bebesfelices.api.controller;

import com.bebesfelices.api.service.CategoryPageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CategoryPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {
            CategoryPageService.EDUCATIONAL_TOYS_SLUG,
            CategoryPageService.MOVEMENT_SLUG,
            CategoryPageService.AUTONOMY_SLUG,
            CategoryPageService.GIFTS_SLUG,
            CategoryPageService.SUSTAINABLE_SLUG
    })
    void returnsPublishedCategoryIndexes(String slug) throws Exception {
        mockMvc.perform(get("/api/category-pages/{slug}", slug))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.slug").value(slug))
                .andExpect(jsonPath("$.childCollections", not(empty())))
                .andExpect(jsonPath("$.breadcrumbs[0].href").value("/"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/" + slug + "/"));
    }

    @Test
    void returnsNotFoundForUnknownCategory() throws Exception {
        mockMvc.perform(get("/api/category-pages/no-existe"))
                .andExpect(status().isNotFound());
    }
}
