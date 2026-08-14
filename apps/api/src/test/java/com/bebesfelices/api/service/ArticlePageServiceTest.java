package com.bebesfelices.api.service;

import com.bebesfelices.api.dto.PageStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArticlePageServiceTest {

    private final ArticlePageService service = new ArticlePageService();

    @Test
    void publishesTheThreeCircuitArticles() {
        assertThat(service.publishedSlugs()).containsExactly(
                ArticlePageService.CHOOSE_BY_AGE_SLUG,
                ArticlePageService.SKILLS_3_SLUG,
                ArticlePageService.METHODOLOGY_SLUG
        );

        for (String slug : service.publishedSlugs()) {
            var page = service.getBySlug(slug).orElseThrow();
            assertThat(page.status()).isEqualTo(PageStatus.PUBLISHED);
            assertThat(page.header().h1()).isNotBlank();
            assertThat(page.sections()).isNotEmpty();
            assertThat(page.faq()).isNotEmpty();
            assertThat(page.relatedLinks())
                    .extracting(link -> link.href())
                    .contains(EditorialDefaults.HUB_3_HREF);
        }
    }

    @Test
    void skillsAndBuyingGuideBreadcrumbsReturnToTheThreeYearHub() {
        var guide = service.getBySlug(ArticlePageService.CHOOSE_BY_AGE_SLUG).orElseThrow();
        var skills = service.getBySlug(ArticlePageService.SKILLS_3_SLUG).orElseThrow();

        assertThat(guide.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.HUB_3_HREF);
        assertThat(skills.breadcrumbs().get(1).href()).isEqualTo(EditorialDefaults.HUB_3_HREF);
        assertThat(guide.canonicalPath()).isEqualTo("/guias/como-elegir-juguetes-por-edad/");
        assertThat(skills.canonicalPath()).isEqualTo("/guias/habilidades-3-anos/");
    }

    @Test
    void methodologyIsTransversalAndStillLinksBackToTheHub() {
        var page = service.getBySlug(ArticlePageService.METHODOLOGY_SLUG).orElseThrow();

        assertThat(page.canonicalPath()).isEqualTo("/como-analizamos/");
        assertThat(page.breadcrumbs()).hasSize(2);
        assertThat(page.breadcrumbs().get(1).href()).isEqualTo("/como-analizamos/");
        assertThat(page.relatedLinks().get(0).href()).isEqualTo(EditorialDefaults.HUB_3_HREF);
    }

    @Test
    void returnsEmptyForAnUnknownSlug() {
        assertThat(service.getBySlug("no-existe")).isEmpty();
    }
}
