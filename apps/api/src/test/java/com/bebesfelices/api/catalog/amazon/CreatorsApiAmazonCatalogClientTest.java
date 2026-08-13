package com.bebesfelices.api.catalog.amazon;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class CreatorsApiAmazonCatalogClientTest {

    @Test
    void mapsAGetItemsResponse() {
        AmazonCreatorsProperties properties = configuredProperties();
        AmazonOAuthTokenProvider tokenProvider = fixedTokenProvider(properties);

        RestClient.Builder builder = RestClient.builder()
                .baseUrl("https://creatorsapi.amazon");
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
        server.expect(requestTo("https://creatorsapi.amazon/catalog/v1/getItems"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header("Authorization", "Bearer access-token"))
                .andExpect(header("x-marketplace", "www.amazon.es"))
                .andExpect(content().json("""
                        {
                          "itemIds": ["B012345678"],
                          "itemIdType": "ASIN",
                          "marketplace": "www.amazon.es",
                          "partnerTag": "bebesfelices-21",
                          "partnerType": "Associates",
                          "resources": [
                            "images.primary.large",
                            "images.primary.small",
                            "itemInfo.title"
                          ]
                        }
                        """))
                .andRespond(withSuccess("""
                        {
                          "itemsResult": {
                            "items": [{
                              "asin": "B012345678",
                              "detailPageURL": "https://www.amazon.es/dp/B012345678?tag=bebesfelices-21",
                              "images": {
                                "primary": {
                                  "large": {"url": "https://m.media-amazon.com/image.jpg"}
                                }
                              },
                              "itemInfo": {
                                "title": {"displayValue": "Título de Amazon"}
                              }
                            }]
                          }
                        }
                        """, MediaType.APPLICATION_JSON));

        CreatorsApiAmazonCatalogClient client = new CreatorsApiAmazonCatalogClient(
                builder.build(),
                tokenProvider,
                properties
        );

        Optional<AmazonProductSnapshot> result =
                client.fetchByAsin("B012345678", "www.amazon.es");

        assertThat(result).contains(new AmazonProductSnapshot(
                "B012345678",
                "www.amazon.es",
                "Título de Amazon",
                "https://www.amazon.es/dp/B012345678?tag=bebesfelices-21",
                "https://m.media-amazon.com/image.jpg"
        ));
        server.verify();
    }

    @Test
    void returnsEmptyWhenAmazonFails() {
        AmazonCreatorsProperties properties = configuredProperties();
        AmazonOAuthTokenProvider tokenProvider = fixedTokenProvider(properties);

        RestClient.Builder builder = RestClient.builder()
                .baseUrl("https://creatorsapi.amazon");
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
        server.expect(requestTo("https://creatorsapi.amazon/catalog/v1/getItems"))
                .andRespond(withServerError());

        CreatorsApiAmazonCatalogClient client = new CreatorsApiAmazonCatalogClient(
                builder.build(),
                tokenProvider,
                properties
        );

        assertThat(client.fetchByAsin("B012345678", "www.amazon.es")).isEmpty();
        server.verify();
    }

    private AmazonOAuthTokenProvider fixedTokenProvider(
            AmazonCreatorsProperties properties
    ) {
        return new AmazonOAuthTokenProvider(RestClient.create(), properties) {
            @Override
            public synchronized String accessToken() {
                return "access-token";
            }
        };
    }

    private AmazonCreatorsProperties configuredProperties() {
        AmazonCreatorsProperties properties = new AmazonCreatorsProperties();
        properties.setCredentialId("credential-id");
        properties.setCredentialSecret("credential-secret");
        properties.setPartnerTag("bebesfelices-21");
        return properties;
    }
}
