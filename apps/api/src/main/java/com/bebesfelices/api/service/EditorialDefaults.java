package com.bebesfelices.api.service;

import com.bebesfelices.api.dto.shared.Affiliation;
import com.bebesfelices.api.dto.shared.LegalLink;
import com.bebesfelices.api.dto.shared.TrustAuthority;

import java.util.List;

/**
 * Textos y enlaces compartidos por las páginas editoriales del circuito de 3 años.
 */
final class EditorialDefaults {

    static final String SITE_URL = "https://bebesfelices.es";
    static final String HUB_3_HREF = "/por-edad/3-anos/";
    static final String HUB_3_LABEL = "3 años";
    static final String PUBLISHED_AT = "2026-08-14";
    static final String UPDATED_AT = "2026-08-14";
    static final String AUTHOR_NAME = "Equipo editorial BebesFelices";
    static final String AUTHOR_ROLE = "Redacción";

    private EditorialDefaults() {
    }

    static TrustAuthority trustAuthority() {
        return new TrustAuthority(
                "Seleccionamos productos con criterios explícitos de edad, seguridad, utilidad y relación calidad-precio. Distinguimos entre investigación, opiniones de compradores y experiencia propia; no inventamos puntuaciones.",
                List.of(
                        "Edad recomendada y encaje con desarrollo y seguridad.",
                        "Utilidad educativa y facilidad de uso en casa.",
                        "Durabilidad y opiniones reales de compradores.",
                        "Relación calidad-precio y actualización del contenido."
                ),
                List.of(
                        "Aviso visible de Amazon Afiliados.",
                        "Fecha de actualización en la página.",
                        "Enlace a la metodología editorial.",
                        "Sin importes, valoraciones ni puntuaciones inventadas."
                )
        );
    }

    static Affiliation affiliation() {
        return new Affiliation(
                "Bebes Felices participa en el Programa de Afiliados de Amazon. Algunos enlaces de esta página son enlaces de afiliado. Esto significa que podemos recibir una comisión si realizas una compra, sin que el precio cambie para ti.",
                "Algunos enlaces pueden ser de afiliado de Amazon."
        );
    }

    static List<LegalLink> legalLinks() {
        return List.of(
                new LegalLink("Aviso legal", "/aviso-legal/"),
                new LegalLink("Política de privacidad", "/politica-privacidad/"),
                new LegalLink("Política de cookies", "/politica-cookies/"),
                new LegalLink("Condiciones de uso", "/condiciones-uso/"),
                new LegalLink("Información sobre afiliación", "/informacion-afiliacion/")
        );
    }

    static String canonical(String path) {
        return SITE_URL + path;
    }
}
