package com.bebesfelices.api.controller;

import com.bebesfelices.api.service.ArticlePageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ArticlePageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {
            ArticlePageService.CHOOSE_BY_AGE_SLUG,
            ArticlePageService.SKILLS_3_SLUG,
            ArticlePageService.METHODOLOGY_SLUG
    })
    void returnsPublishedArticles(String slug) throws Exception {
        mockMvc.perform(get("/api/article-pages/{slug}", slug))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.slug").value(slug))
                .andExpect(jsonPath("$.header.h1", not(emptyOrNullString())))
                .andExpect(jsonPath("$.sections", not(org.hamcrest.Matchers.empty())))
                .andExpect(jsonPath("$.relatedLinks[0].href").value("/por-edad/3-anos/"));
    }

    @Test
    void returnsTheFourYearSkillsGuide() throws Exception {
        mockMvc.perform(get("/api/article-pages/{slug}", ArticlePageService.SKILLS_4_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.relatedLinks[0].href").value("/por-edad/4-anos/"));
    }

    @Test
    void returnsTheFiveYearSkillsGuide() throws Exception {
        mockMvc.perform(get("/api/article-pages/{slug}", ArticlePageService.SKILLS_5_SLUG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PUBLISHED"))
                .andExpect(jsonPath("$.canonicalPath").value("/guias/habilidades-5-anos/"))
                .andExpect(jsonPath("$.breadcrumbs[1].href").value("/por-edad/5-anos/"))
                .andExpect(jsonPath("$.relatedLinks[0].href").value("/por-edad/5-anos/"));
    }

    @Test
    void returnsNotFoundForAnUnknownArticle() throws Exception {
        mockMvc.perform(get("/api/article-pages/no-existe"))
                .andExpect(status().isNotFound());
    }
}
