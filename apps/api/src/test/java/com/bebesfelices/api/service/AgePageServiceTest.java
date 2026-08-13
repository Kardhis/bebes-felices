package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.AmazonEnrichedProductCatalog;
import com.bebesfelices.api.catalog.ManualProductCatalog;
import com.bebesfelices.api.catalog.amazon.AmazonCreatorsProperties;
import com.bebesfelices.api.catalog.amazon.AmazonProductSnapshot;
import com.bebesfelices.api.dto.AgePageResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AgePageServiceTest {

    private final AgePageService service = new AgePageService(new ManualProductCatalog());

    @ParameterizedTest
    @ValueSource(strings = {"3-anos", "4-anos", "5-anos"})
    void returnsAResponseForEachValidSlug(String slug) {
        Optional<AgePageResponse> page = service.getBySlug(slug);

        assertThat(page).isPresent();
        assertThat(page.get().slug()).isEqualTo(slug);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2-anos", "6-anos", "3-anios", ""})
    void returnsEmptyForAnyOtherSlug(String slug) {
        assertThat(service.getBySlug(slug)).isEmpty();
    }

    @Test
    void differentiatesContentAcrossAges() {
        AgePageResponse page3 = service.getBySlug("3-anos").orElseThrow();
        AgePageResponse page4 = service.getBySlug("4-anos").orElseThrow();
        AgePageResponse page5 = service.getBySlug("5-anos").orElseThrow();

        assertThat(page3.header().h1()).isNotEqualTo(page4.header().h1());
        assertThat(page3.header().introductionParagraphs()).isNotEqualTo(page4.header().introductionParagraphs());
        assertThat(page4.header().introductionParagraphs()).isNotEqualTo(page5.header().introductionParagraphs());
        assertThat(page3.header().introductionParagraphs()).hasSizeGreaterThan(1);

        assertThat(page3.seo().canonicalUrl()).isEqualTo("https://bebesfelices.es/por-edad/3-anos/");
        assertThat(page4.seo().canonicalUrl()).isEqualTo("https://bebesfelices.es/por-edad/4-anos/");

        // La selección destacada varía según disponibilidad por edad en el catálogo.
        List<String> titles3 = page3.featuredSelection().stream().map(AgePageResponse.FeaturedProduct::title).toList();
        List<String> titles5 = page5.featuredSelection().stream().map(AgePageResponse.FeaturedProduct::title).toList();
        assertThat(titles3).isNotEqualTo(titles5);
    }

    @Test
    void featuredSelectionSizeStaysWithinEditorialRange() {
        for (String slug : List.of("3-anos", "4-anos", "5-anos")) {
            AgePageResponse page = service.getBySlug(slug).orElseThrow();
            assertThat(page.featuredSelection().size()).isBetween(5, 10);
        }
    }

    @Test
    void doesNotInventCommercialDataInFeaturedSelection() {
        AgePageResponse page = service.getBySlug("4-anos").orElseThrow();

        assertThat(page.featuredSelection()).isNotEmpty();
        assertThat(page.featuredSelection())
                .allSatisfy(product -> assertThat(product.affiliateHref()).isNull());
    }

    @Test
    void exposesOnlyAffiliateLinksValidatedByTheCatalog() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setCredentialId("credential-id");
        properties.setCredentialSecret("credential-secret");
        properties.setPartnerTag("bebesfelices-21");
        properties.setProductAsins(Map.of("bici-sin-pedales-basica", "B012345678"));
        AmazonEnrichedProductCatalog catalog = new AmazonEnrichedProductCatalog(
                new ManualProductCatalog(),
                (asin, marketplace) -> Optional.of(new AmazonProductSnapshot(
                        asin,
                        marketplace,
                        "Título de Amazon",
                        "https://www.amazon.es/dp/" + asin + "?tag=bebesfelices-21",
                        null
                )),
                properties
        );

        AgePageResponse page =
                new AgePageService(catalog).getBySlug("3-anos").orElseThrow();

        assertThat(page.featuredSelection())
                .filteredOn(product -> product.title().equals("Bicicleta sin pedales básica"))
                .extracting(AgePageResponse.FeaturedProduct::affiliateHref)
                .containsExactly("https://www.amazon.es/dp/B012345678?tag=bebesfelices-21");
    }

    @Test
    void breadcrumbsHaveHomeAgeHubAndCurrentAge() {
        AgePageResponse page = service.getBySlug("5-anos").orElseThrow();

        assertThat(page.breadcrumbs()).hasSize(3);
        assertThat(page.breadcrumbs().get(0).label()).isEqualTo("Inicio");
        assertThat(page.breadcrumbs().get(2).label()).isEqualTo("5 años");
    }

    @Test
    void otherAgesExcludesTheCurrentAgeAndListsTheOtherTwo() {
        AgePageResponse page = service.getBySlug("4-anos").orElseThrow();

        assertThat(page.otherAges()).hasSize(2);
        assertThat(page.otherAges()).extracting("ageLabel")
                .containsExactlyInAnyOrder("3 años", "5 años");
    }

    @Test
    void faqAndAffiliationNoticeAreAlwaysPresent() {
        for (String slug : List.of("3-anos", "4-anos", "5-anos")) {
            AgePageResponse page = service.getBySlug(slug).orElseThrow();
            assertThat(page.faq()).isNotEmpty();
            assertThat(page.affiliation().noticeText()).isNotBlank();
        }
    }
}
