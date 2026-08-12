package com.bebesfelices.api.catalog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class AffiliateLinkTest {

    @Test
    void acceptsAValidatedAmazonEsUrl() {
        AffiliateLink link = new AffiliateLink("https://www.amazon.es/dp/EXAMPLE", "Amazon");

        assertThat(link.url()).isEqualTo("https://www.amazon.es/dp/EXAMPLE");
        assertThat(link.retailer()).isEqualTo("Amazon");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "http://www.amazon.es/dp/EXAMPLE",
            "https://www.amazon.com/dp/EXAMPLE",
            "https://amazon.es/dp/EXAMPLE",
            "https://malicious-site.com/www.amazon.es",
            "not-a-url"
    })
    void rejectsUrlsThatAreNotHttpsAmazonEs(String url) {
        assertThatIllegalArgumentException().isThrownBy(() -> new AffiliateLink(url, "Amazon"));
    }

    @Test
    void rejectsBlankUrl() {
        assertThatIllegalArgumentException().isThrownBy(() -> new AffiliateLink("", "Amazon"));
        assertThatIllegalArgumentException().isThrownBy(() -> new AffiliateLink(null, "Amazon"));
    }
}
