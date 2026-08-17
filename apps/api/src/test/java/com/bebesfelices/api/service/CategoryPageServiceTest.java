package com.bebesfelices.api.service;

import com.bebesfelices.api.dto.CategoryPageResponse;
import com.bebesfelices.api.dto.PageStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryPageServiceTest {

    @Autowired
    private CategoryPageService categoryPageService;

    @Test
    void returnsEducationalToysIndexWithPublishedChildren() {
        Optional<CategoryPageResponse> page = categoryPageService.getBySlug(
                CategoryPageService.EDUCATIONAL_TOYS_SLUG
        );

        assertThat(page).isPresent();
        assertThat(page.get().status()).isEqualTo(PageStatus.PUBLISHED);
        assertThat(page.get().canonicalPath()).isEqualTo("/juguetes-educativos/");
        assertThat(page.get().seo().canonicalUrl()).isEqualTo("https://bebesfelices.es/juguetes-educativos/");
        assertThat(page.get().childCollections())
                .extracting(link -> link.href())
                .containsExactly(
                        "/juguetes-educativos/juegos-montessori/",
                        "/juguetes-educativos/puzles/",
                        "/juguetes-educativos/juegos-stem/",
                        "/juguetes-educativos/juegos-de-mesa/"
                );
    }

    @Test
    void returnsMovementIndexWithPublishedChildren() {
        Optional<CategoryPageResponse> page = categoryPageService.getBySlug(
                CategoryPageService.MOVEMENT_SLUG
        );

        assertThat(page).isPresent();
        assertThat(page.get().canonicalPath()).isEqualTo("/movimiento/");
        assertThat(page.get().childCollections())
                .extracting(link -> link.href())
                .containsExactly(
                        "/movimiento/patinetes/",
                        "/movimiento/bicicletas-sin-pedales/"
                );
    }

    @Test
    void returnsAutonomyIndexWithPublishedChildren() {
        Optional<CategoryPageResponse> page = categoryPageService.getBySlug(
                CategoryPageService.AUTONOMY_SLUG
        );

        assertThat(page).isPresent();
        assertThat(page.get().canonicalPath()).isEqualTo("/autonomia/");
        assertThat(page.get().childCollections())
                .extracting(link -> link.href())
                .containsExactly(
                        "/autonomia/torres-de-aprendizaje/",
                        "/autonomia/vajilla-infantil/"
                );
    }

    @Test
    void returnsGiftsIndexWithPublishedChildren() {
        Optional<CategoryPageResponse> page = categoryPageService.getBySlug(
                CategoryPageService.GIFTS_SLUG
        );

        assertThat(page).isPresent();
        assertThat(page.get().canonicalPath()).isEqualTo("/regalos/");
        assertThat(page.get().childCollections())
                .extracting(link -> link.href())
                .containsExactly(
                        "/regalos/ideas-regalo-3-anos/",
                        "/regalos/ideas-regalo-4-anos/",
                        "/regalos/ideas-regalo-5-anos/"
                );
    }

    @Test
    void collectionMembershipMatchesFrontendRegistry() {
        assertThat(categoryPageService.collectionSlugsForCategory(CategoryPageService.EDUCATIONAL_TOYS_SLUG))
                .containsExactly(
                        CollectionPageService.MONTESSORI_SLUG,
                        CollectionPageService.PUZZLES_SLUG,
                        CollectionPageService.STEM_SLUG,
                        CollectionPageService.BOARD_GAMES_SLUG
                );
        assertThat(categoryPageService.collectionSlugsForCategory(CategoryPageService.MOVEMENT_SLUG))
                .containsExactly(
                        CollectionPageService.SCOOTERS_SLUG,
                        CollectionPageService.BALANCE_BIKES_SLUG
                );
        assertThat(categoryPageService.collectionSlugsForCategory(CategoryPageService.AUTONOMY_SLUG))
                .containsExactly(
                        CollectionPageService.TOWERS_SLUG,
                        CollectionPageService.TABLEWARE_SLUG
                );
        assertThat(categoryPageService.collectionSlugsForCategory(CategoryPageService.GIFTS_SLUG))
                .containsExactly(
                        CollectionPageService.GIFTS_3_SLUG,
                        CollectionPageService.GIFTS_4_SLUG,
                        CollectionPageService.GIFTS_5_SLUG
                );
    }

    @Test
    void returnsEmptyForUnknownCategory() {
        assertThat(categoryPageService.getBySlug("desconocida")).isEmpty();
    }

    @Test
    void publishedSlugsIncludeAllCategoryIndexes() {
        assertThat(categoryPageService.publishedSlugs()).containsExactly(
                CategoryPageService.EDUCATIONAL_TOYS_SLUG,
                CategoryPageService.MOVEMENT_SLUG,
                CategoryPageService.AUTONOMY_SLUG,
                CategoryPageService.GIFTS_SLUG
        );
    }

    @Test
    void childCollectionsReuseCollectionTitlesAndPaths() {
        CategoryPageResponse page = categoryPageService.getBySlug(CategoryPageService.GIFTS_SLUG).orElseThrow();

        assertThat(page.childCollections().get(0).title()).contains("3 años");
        assertThat(page.childCollections().get(0).href()).isEqualTo("/regalos/ideas-regalo-3-anos/");
        assertThat(page.childCollections().get(0).description()).isNotBlank();
    }
}
