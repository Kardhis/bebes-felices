package com.bebesfelices.api.catalog;

import java.net.URI;
import java.util.Set;

/**
 * Enlace comercial validado hacia una tienda autorizada.
 * <p>
 * Solo se aceptan URLs HTTPS hacia dominios de Amazon España. Esto evita
 * introducir enlaces a dominios arbitrarios, tanto si el enlace lo
 * introduce un editor como si en el futuro proviene de una sincronización
 * con la Creators API de Amazon.
 */
public record AffiliateLink(String url, String retailer) {

    private static final Set<String> ALLOWED_HOSTS = Set.of("www.amazon.es");

    public AffiliateLink {
        url = requireValidUrl(url);
    }

    private static String requireValidUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("La URL del enlace de afiliado no puede estar vacía.");
        }

        URI uri;
        try {
            uri = URI.create(url);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("URL de afiliado no válida: " + url, e);
        }

        if (!"https".equals(uri.getScheme())) {
            throw new IllegalArgumentException("El enlace de afiliado debe usar HTTPS: " + url);
        }

        if (uri.getHost() == null || !ALLOWED_HOSTS.contains(uri.getHost())) {
            throw new IllegalArgumentException(
                    "Dominio de afiliado no permitido: " + uri.getHost());
        }

        return url;
    }
}
