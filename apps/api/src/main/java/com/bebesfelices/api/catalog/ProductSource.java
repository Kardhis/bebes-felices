package com.bebesfelices.api.catalog;

/**
 * Origen de los datos de un producto.
 * <p>
 * {@code MANUAL}: introducido editorialmente, sin conexión a Amazon.
 * {@code AMAZON}: sincronizado mediante {@link com.bebesfelices.api.catalog.amazon.AmazonCatalogClient}
 * (todavía sin implementación real conectada en el MVP).
 */
public enum ProductSource {
    MANUAL,
    AMAZON
}
