package com.bebesfelices.api.catalog.amazon;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.time.Clock;
import java.time.Instant;
import java.util.Map;

public class AmazonOAuthTokenProvider {

    private static final long EXPIRY_MARGIN_SECONDS = 60;

    private final RestClient restClient;
    private final AmazonCreatorsProperties properties;
    private final Clock clock;

    private volatile CachedToken cachedToken;

    public AmazonOAuthTokenProvider(RestClient restClient, AmazonCreatorsProperties properties) {
        this(restClient, properties, Clock.systemUTC());
    }

    AmazonOAuthTokenProvider(
            RestClient restClient,
            AmazonCreatorsProperties properties,
            Clock clock
    ) {
        this.restClient = restClient;
        this.properties = properties;
        this.clock = clock;
    }

    public synchronized String accessToken() {
        Instant now = clock.instant();
        if (cachedToken != null && cachedToken.expiresAt().isAfter(now)) {
            return cachedToken.value();
        }

        Map<String, Object> response = restClient.post()
                .uri(properties.tokenEndpoint())
                .body(Map.of(
                        "grant_type", "client_credentials",
                        "client_id", properties.getCredentialId(),
                        "client_secret", properties.getCredentialSecret(),
                        "scope", "creatorsapi::default"
                ))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        if (response == null
                || !(response.get("access_token") instanceof String token)
                || token.isBlank()
                || !(response.get("expires_in") instanceof Number expiresIn)
                || expiresIn.longValue() <= EXPIRY_MARGIN_SECONDS) {
            throw new IllegalStateException("Respuesta OAuth inválida de Amazon Creators API.");
        }

        cachedToken = new CachedToken(
                token,
                now.plusSeconds(expiresIn.longValue() - EXPIRY_MARGIN_SECONDS)
        );
        return token;
    }

    private record CachedToken(String value, Instant expiresAt) {
    }
}
