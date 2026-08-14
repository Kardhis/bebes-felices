package com.bebesfelices.api.controller;

import com.bebesfelices.api.dto.CollectionPageResponse;
import com.bebesfelices.api.service.CollectionPageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/collection-pages", produces = MediaType.APPLICATION_JSON_VALUE)
public class CollectionPageController {

    private final CollectionPageService collectionPageService;

    public CollectionPageController(CollectionPageService collectionPageService) {
        this.collectionPageService = collectionPageService;
    }

    @GetMapping("/{slug}")
    public CollectionPageResponse getCollectionPage(@PathVariable String slug) {
        return collectionPageService.getBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Collection page not found: " + slug
                ));
    }
}
