package com.bebesfelices.api.dto;

import com.bebesfelices.api.dto.shared.Affiliation;
import com.bebesfelices.api.dto.shared.LegalLink;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import com.bebesfelices.api.dto.shared.TrustAuthority;

import java.util.List;

/**
 * Contrato editorial de una comparativa de productos.
 * Los datos comerciales solo se exponen mediante {@code affiliateHref} cuando
 * el catálogo ha construido y validado el enlace.
 */
public record ComparisonPageResponse(
        Seo seo,
        PageStatus status,
        String slug,
        int targetAge,
        List<Breadcrumb> breadcrumbs,
        Header header,
        List<QuickNavItem> quickNavigation,
        List<QuickSummaryItem> quickSummary,
        Methodology methodology,
        List<Entry> entries,
        BuyingGuide buyingGuide,
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
            String h1,
            String subtitle,
            List<String> introductionParagraphs
    ) {
    }

    public record QuickNavItem(String label, String anchor) {
    }

    public record QuickSummaryItem(
            String label,
            String productId,
            String reason
    ) {
    }

    public record Methodology(
            String introduction,
            List<Criterion> criteria
    ) {
    }

    public record Criterion(String name, String description) {
    }

    public record Entry(
            int rank,
            String productId,
            String title,
            String bestFor,
            String editorialSummary,
            List<String> pros,
            List<String> cons,
            String ageRange,
            List<CriterionNote> criteriaNotes,
            String affiliateHref
    ) {
    }

    public record CriterionNote(String criterion, String note) {
    }

    public record BuyingGuide(List<Section> sections) {
    }

    public record Section(String title, List<String> paragraphs) {
    }

    public record Faq(String question, String answer) {
    }

    public record Author(String name, String role) {
    }
}
