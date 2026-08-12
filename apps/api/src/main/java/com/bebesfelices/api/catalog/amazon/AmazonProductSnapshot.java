package com.bebesfelices.api.catalog.amazon;

/**
 * Forma prevista de los datos de producto que devolverá la Creators API de
 * Amazon una vez esté conectada (operación equivalente a {@code GetItems}).
 * <p>
 * No se usa todavía en ninguna respuesta pública: solo fija el contrato del
 * futuro cliente para que la integración real no requiera cambiar el resto
 * de la aplicación.
 */
public record AmazonProductSnapshot(
        String asin,
        String marketplace,
        String title,
        String detailPageUrl,
        String primaryImageUrl
) {
}
