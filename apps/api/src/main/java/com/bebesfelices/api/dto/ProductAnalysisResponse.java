package com.bebesfelices.api.dto;

import com.bebesfelices.api.dto.shared.Affiliation;
import com.bebesfelices.api.dto.shared.LegalLink;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import com.bebesfelices.api.dto.shared.TrustAuthority;

import java.util.List;

/**
 * Contrato de un análisis editorial de un producto del catálogo interno.
 * No incluye precio, valoración ni oferta: solo contenido revisado y, si existe,
 * un {@code affiliateHref} ya validado.
 */
public record ProductAnalysisResponse(
        Seo seo,
        PageStatus status,
        String productId,
        String canonicalPath,
        List<Breadcrumb> breadcrumbs,
        Header header,
        String category,
        String ageRange,
        String forWhom,
        String editorialSummary,
        List<String> pros,
        List<String> cons,
        List<String> safetyNotes,
        List<String> buyingChecks,
        String affiliateHref,
        List<LinkItem> relatedLinks,
        TrustAuthority trustAuthority,
        Affiliation affiliation,
        List<LegalLink> legalLinks,
        Author author,
        String publishedAt,
        String updatedAt
) {
    public record Breadcrumb(String label, String href) {
    }

    public record Header(
            String kicker,
            String h1,
            List<String> introductionParagraphs
    ) {
    }

    public record Author(String name, String role) {
    }
}
