package com.bebesfelices.api.dto;

import com.bebesfelices.api.dto.shared.Affiliation;
import com.bebesfelices.api.dto.shared.LegalLink;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import com.bebesfelices.api.dto.shared.TrustAuthority;

import java.util.List;

/**
 * Contrato de una guía o artículo editorial (sin ranking de productos).
 */
public record ArticlePageResponse(
        Seo seo,
        PageStatus status,
        String slug,
        String canonicalPath,
        List<Breadcrumb> breadcrumbs,
        Header header,
        List<Section> sections,
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

    public record Section(String id, String title, List<String> paragraphs) {
    }

    public record Faq(String question, String answer) {
    }

    public record Author(String name, String role) {
    }
}
