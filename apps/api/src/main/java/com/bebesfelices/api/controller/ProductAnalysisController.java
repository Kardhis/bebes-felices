package com.bebesfelices.api.controller;

import com.bebesfelices.api.dto.ProductAnalysisResponse;
import com.bebesfelices.api.service.ProductAnalysisPageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/product-pages", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductAnalysisController {

    private final ProductAnalysisPageService productAnalysisPageService;

    public ProductAnalysisController(ProductAnalysisPageService productAnalysisPageService) {
        this.productAnalysisPageService = productAnalysisPageService;
    }

    @GetMapping("/{productId}")
    public ProductAnalysisResponse getProductPage(@PathVariable String productId) {
        return productAnalysisPageService.getByProductId(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product page not found: " + productId
                ));
    }
}
