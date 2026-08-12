package com.bebesfelices.api.catalog.amazon;

import java.util.Optional;

/**
 * Puerto hacia la Creators API de Amazon (sucesora de Product Advertising
 * API 5.0).
 * <p>
 * No existe todavía ninguna implementación registrada como bean de Spring:
 * conectar este puerto requiere estar inscrito en el Programa de Afiliados
 * de Amazon España, credenciales OAuth 2.0 (Credential ID/Secret) y al
 * menos 10 ventas cualificadas en los últimos 30 días para acceder a la
 * API. Mientras tanto, {@link com.bebesfelices.api.catalog.ManualProductCatalog}
 * cubre el catálogo del MVP.
 * <p>
 * Cuando se disponga de acceso, una implementación de esta interfaz podrá
 * sincronizar productos {@code AMAZON} sin cambiar los DTO expuestos por la
 * API ni los componentes del frontend, que ya consumen el modelo
 * normalizado {@link com.bebesfelices.api.catalog.Product}.
 */
public interface AmazonCatalogClient {

    Optional<AmazonProductSnapshot> fetchByAsin(String asin, String marketplace);
}
