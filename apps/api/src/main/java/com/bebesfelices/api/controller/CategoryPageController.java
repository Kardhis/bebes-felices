package com.bebesfelices.api.controller;

import com.bebesfelices.api.dto.CategoryPageResponse;
import com.bebesfelices.api.service.CategoryPageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/category-pages", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryPageController {

    private final CategoryPageService categoryPageService;

    public CategoryPageController(CategoryPageService categoryPageService) {
        this.categoryPageService = categoryPageService;
    }

    @GetMapping("/{slug}")
    public CategoryPageResponse getCategoryPage(@PathVariable String slug) {
        return categoryPageService.getBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category page not found: " + slug
                ));
    }
}
