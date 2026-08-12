package com.bebesfelices.api.controller;

import com.bebesfelices.api.dto.AgePageResponse;
import com.bebesfelices.api.service.AgePageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/age-pages", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgePageController {

    private final AgePageService agePageService;

    public AgePageController(AgePageService agePageService) {
        this.agePageService = agePageService;
    }

    @GetMapping("/{slug}")
    public AgePageResponse getAgePage(@PathVariable String slug) {
        return agePageService.getBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Age page not found: " + slug));
    }
}
