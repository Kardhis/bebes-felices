package com.bebesfelices.api.catalog;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de acceso al catálogo de productos.
 * <p>
 * La implementación actual ({@link ManualProductCatalog}) es manual y en
 * memoria. En el futuro podrá coexistir o sustituirse por una
 * implementación que sincronice productos {@code AMAZON} a través de
 * {@link com.bebesfelices.api.catalog.amazon.AmazonCatalogClient}, sin que
 * el resto de la aplicación (DTO, servicios, controladores) necesite
 * cambiar.
 */
public interface ProductCatalog {

    Optional<Product> findById(String id);

    List<Product> findByIds(List<String> ids);
}
