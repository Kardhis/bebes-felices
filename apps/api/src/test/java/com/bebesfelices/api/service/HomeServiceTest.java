package com.bebesfelices.api.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HomeServiceTest {

    @Test
    void buildsPublishedHomeFromSharedRouteConstants() {
        var home = new HomeService("https://staging.bebesfelices.es/").getHome();

        assertThat(home.seo().canonicalUrl()).isEqualTo("https://staging.bebesfelices.es/");
        assertThat(home.hero().imageUrl()).startsWith("/images/");
        assertThat(home.mainCategories())
                .extracting(item -> item.href())
                .containsExactly(
                        "/juguetes-educativos/",
                        "/movimiento/",
                        "/autonomia/",
                        "/regalos/",
                        "/sostenibles/"
                );
        assertThat(home.recentComparisons())
                .extracting(item -> item.href())
                .allMatch(href -> href.startsWith("/comparativas/") && href.endsWith("/"));
        assertThat(home.legalLinks()).isNotEmpty();
        assertThat(home.updatedAt()).isEqualTo("2026-08-17");
    }
}
