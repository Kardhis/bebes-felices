package com.bebesfelices.api.dto;

import com.bebesfelices.api.dto.shared.AgeLink;
import com.bebesfelices.api.dto.shared.Affiliation;
import com.bebesfelices.api.dto.shared.LegalLink;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import com.bebesfelices.api.dto.shared.TrustAuthority;

import java.util.List;

/**
 * Respuesta de datos para la home (página inicial SEO).
 */
public record HomeResponse(
        Seo seo,
        Hero hero,
        List<AgeLink> ageNavigation,
        List<LinkItem> mainCategories,
        List<LinkItem> featuredGuides,
        List<LinkItem> recentComparisons,
        TrustAuthority trustAuthority,
        Affiliation affiliation,
        List<LegalLink> legalLinks,
        String updatedAt
) {
    public record Hero(
            String brand,
            String h1,
            String valueProposition,
            String primaryCtaLabel,
            String primaryCtaHref,
            String secondaryCtaLabel,
            String secondaryCtaHref,
            String imageUrl,
            String imageAlt
    ) {
    }
}
