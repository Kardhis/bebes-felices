package com.bebesfelices.api.dto;

import com.bebesfelices.api.dto.shared.Affiliation;
import com.bebesfelices.api.dto.shared.LegalLink;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import com.bebesfelices.api.dto.shared.TrustAuthority;

import java.util.List;

/**
 * Contrato de una página de categoría, regalos o sostenibles.
 * No es un ranking numerado: presenta criterios y productos del catálogo.
 */
public record CollectionPageResponse(
        Seo seo,
        PageStatus status,
        String slug,
        String canonicalPath,
        int hubAge,
        List<Breadcrumb> breadcrumbs,
        Header header,
        List<String> buyingCriteria,
        List<CollectionProduct> products,
        List<Faq> faq,
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

    public record CollectionProduct(
            String title,
            String category,
            String reason,
            String ageRange,
            String href,
            String affiliateHref,
            String ctaLabel
    ) {
    }

    public record Faq(String question, String answer) {
    }

    public record Author(String name, String role) {
    }
}
