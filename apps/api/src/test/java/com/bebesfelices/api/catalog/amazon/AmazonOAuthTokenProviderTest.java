package com.bebesfelices.api.catalog.amazon;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class AmazonOAuthTokenProviderTest {

    private static final Clock CLOCK =
            Clock.fixed(Instant.parse("2026-08-13T12:00:00Z"), ZoneOffset.UTC);

    @Test
    void obtainsAndCachesAnAccessToken() {
        AmazonCreatorsProperties properties = configuredProperties();
        RestClient.Builder builder = RestClient.builder();
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
        server.expect(once(), requestTo(properties.tokenEndpoint()))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {
                          "grant_type": "client_credentials",
                          "client_id": "credential-id",
                          "client_secret": "credential-secret",
                          "scope": "creatorsapi::default"
                        }
                        """))
                .andRespond(withSuccess("""
                        {
                          "access_token": "access-token",
                          "token_type": "bearer",
                          "expires_in": 3600
                        }
                        """, MediaType.APPLICATION_JSON));

        AmazonOAuthTokenProvider provider =
                new AmazonOAuthTokenProvider(builder.build(), properties, CLOCK);

        assertThat(provider.accessToken()).isEqualTo("access-token");
        assertThat(provider.accessToken()).isEqualTo("access-token");
        server.verify();
    }

    @Test
    void rejectsAnInvalidTokenResponse() {
        AmazonCreatorsProperties properties = configuredProperties();
        RestClient.Builder builder = RestClient.builder();
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
        server.expect(requestTo(properties.tokenEndpoint()))
                .andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));

        AmazonOAuthTokenProvider provider =
                new AmazonOAuthTokenProvider(builder.build(), properties, CLOCK);

        assertThatThrownBy(provider::accessToken)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("OAuth inválida");
        server.verify();
    }

    private AmazonCreatorsProperties configuredProperties() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setCredentialId("credential-id");
        properties.setCredentialSecret("credential-secret");
        properties.setPartnerTag("bebesfelices-21");
        return properties;
    }
}
