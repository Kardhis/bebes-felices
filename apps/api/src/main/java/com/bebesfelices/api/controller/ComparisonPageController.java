package com.bebesfelices.api.controller;

import com.bebesfelices.api.dto.ComparisonPageResponse;
import com.bebesfelices.api.service.ComparisonPageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/comparison-pages", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComparisonPageController {

    private final ComparisonPageService comparisonPageService;

    public ComparisonPageController(ComparisonPageService comparisonPageService) {
        this.comparisonPageService = comparisonPageService;
    }

    @GetMapping("/{slug}")
    public ComparisonPageResponse getComparisonPage(@PathVariable String slug) {
        return comparisonPageService.getBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Comparison page not found: " + slug
                ));
    }
}
