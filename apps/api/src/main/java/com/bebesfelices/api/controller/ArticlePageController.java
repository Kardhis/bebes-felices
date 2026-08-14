package com.bebesfelices.api.controller;

import com.bebesfelices.api.dto.ArticlePageResponse;
import com.bebesfelices.api.service.ArticlePageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/article-pages", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticlePageController {

    private final ArticlePageService articlePageService;

    public ArticlePageController(ArticlePageService articlePageService) {
        this.articlePageService = articlePageService;
    }

    @GetMapping("/{slug}")
    public ArticlePageResponse getArticlePage(@PathVariable String slug) {
        return articlePageService.getBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Article page not found: " + slug
                ));
    }
}
