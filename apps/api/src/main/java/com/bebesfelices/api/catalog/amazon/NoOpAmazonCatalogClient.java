package com.bebesfelices.api.catalog.amazon;

import java.util.Optional;

/**
 * Mantiene el catálogo editorial operativo cuando Amazon no está configurado.
 */
public class NoOpAmazonCatalogClient implements AmazonCatalogClient {

    @Override
    public Optional<AmazonProductSnapshot> fetchByAsin(String asin, String marketplace) {
        return Optional.empty();
    }
}
