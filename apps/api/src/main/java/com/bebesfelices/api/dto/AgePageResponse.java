package com.bebesfelices.api.dto;

import com.bebesfelices.api.dto.shared.AgeLink;
import com.bebesfelices.api.dto.shared.Affiliation;
import com.bebesfelices.api.dto.shared.LegalLink;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import com.bebesfelices.api.dto.shared.TrustAuthority;

import java.util.List;

/**
 * Respuesta de datos para la página hub de una edad concreta (3, 4 o 5 años).
 * <p>
 * {@code featuredSelection} referencia productos internos del catálogo
 * ({@link com.bebesfelices.api.catalog.ProductCatalog}). {@code affiliateHref}
 * solo estará presente cuando exista un enlace de Amazon validado
 * ({@link com.bebesfelices.api.catalog.AffiliateLink}); en el MVP, al no
 * haber conexión con la Creators API, siempre será {@code null}.
 */
public record AgePageResponse(
        Seo seo,
        int age,
        String ageLabel,
        String slug,
        List<Breadcrumb> breadcrumbs,
        Header header,
        List<QuickNavItem> quickNavigation,
        List<QuickSummaryItem> quickSummary,
        List<NeedGroup> optionsByNeed,
        List<FeaturedProduct> featuredSelection,
        List<DevelopmentSkill> developmentSkills,
        List<String> buyingConsiderations,
        List<LinkItem> featuredGuides,
        List<LinkItem> featuredRankings,
        List<LinkItem> giftIdeas,
        List<LinkItem> informativeArticles,
        List<Faq> faq,
        List<AgeLink> otherAges,
        TrustAuthority trustAuthority,
        Affiliation affiliation,
        List<LegalLink> legalLinks,
        Author author,
        String publishedAt,
        String updatedAt
) {
    public record Breadcrumb(String label, String href) {
    }

    public record Header(String h1, List<String> introductionParagraphs) {
    }

    public record QuickNavItem(String label, String anchor) {
    }

    public record QuickSummaryItem(String need, String recommendation, String href) {
    }

    public record NeedGroup(String title, String anchor, List<LinkItem> items) {
    }

    public record FeaturedProduct(
            String title,
            String category,
            String reason,
            String ageRange,
            String href,
            String affiliateHref
    ) {
    }

    public record DevelopmentSkill(String skill, String description) {
    }

    public record Faq(String question, String answer) {
    }

    public record Author(String name, String role) {
    }
}
