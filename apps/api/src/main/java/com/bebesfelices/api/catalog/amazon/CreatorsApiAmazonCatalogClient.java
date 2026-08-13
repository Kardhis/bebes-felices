package com.bebesfelices.api.catalog.amazon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CreatorsApiAmazonCatalogClient implements AmazonCatalogClient {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CreatorsApiAmazonCatalogClient.class);

    private final RestClient restClient;
    private final AmazonOAuthTokenProvider tokenProvider;
    private final AmazonCreatorsProperties properties;

    public CreatorsApiAmazonCatalogClient(
            RestClient restClient,
            AmazonOAuthTokenProvider tokenProvider,
            AmazonCreatorsProperties properties
    ) {
        this.restClient = restClient;
        this.tokenProvider = tokenProvider;
        this.properties = properties;
    }

    @Override
    public Optional<AmazonProductSnapshot> fetchByAsin(String asin, String marketplace) {
        if (asin == null || asin.isBlank() || marketplace == null || marketplace.isBlank()) {
            return Optional.empty();
        }

        try {
            Map<String, Object> response = restClient.post()
                    .uri("/catalog/v1/getItems")
                    .header("Authorization", "Bearer " + tokenProvider.accessToken())
                    .header("x-marketplace", marketplace)
                    .body(Map.of(
                            "itemIds", List.of(asin),
                            "itemIdType", "ASIN",
                            "marketplace", marketplace,
                            "partnerTag", properties.getPartnerTag(),
                            "partnerType", "Associates",
                            "resources", List.of(
                                    "images.primary.large",
                                    "images.primary.small",
                                    "itemInfo.title"
                            )
                    ))
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return findItem(response, asin, marketplace);
        } catch (RestClientResponseException exception) {
            LOGGER.warn(
                    "Amazon Creators API rechazó el ASIN {} con estado {}.",
                    asin,
                    exception.getStatusCode().value()
            );
            return Optional.empty();
        } catch (RuntimeException exception) {
            LOGGER.warn(
                    "No se pudo enriquecer el ASIN {} desde Amazon Creators API: {}",
                    asin,
                    exception.getMessage()
            );
            return Optional.empty();
        }
    }

    private Optional<AmazonProductSnapshot> findItem(
            Map<String, Object> response,
            String requestedAsin,
            String marketplace
    ) {
        Map<?, ?> itemsResult = nestedMap(response, "itemsResult");
        Object rawItems = itemsResult == null ? null : itemsResult.get("items");
        if (!(rawItems instanceof List<?> items)) {
            return Optional.empty();
        }

        return items.stream()
                .filter(Map.class::isInstance)
                .map(Map.class::cast)
                .filter(item -> requestedAsin.equals(item.get("asin")))
                .map(item -> toSnapshot(item, marketplace))
                .filter(Optional::isPresent)
                .map(Optional::orElseThrow)
                .findFirst();
    }

    private Optional<AmazonProductSnapshot> toSnapshot(Map<?, ?> item, String marketplace) {
        String asin = stringValue(item.get("asin"));
        String detailPageUrl = stringValue(item.get("detailPageURL"));
        if (asin == null || detailPageUrl == null) {
            return Optional.empty();
        }

        Map<?, ?> itemInfo = nestedMap(item, "itemInfo");
        Map<?, ?> title = nestedMap(itemInfo, "title");
        String titleValue = title == null ? null : stringValue(title.get("displayValue"));

        Map<?, ?> images = nestedMap(item, "images");
        Map<?, ?> primary = nestedMap(images, "primary");
        String imageUrl = imageUrl(primary, "large");
        if (imageUrl == null) {
            imageUrl = imageUrl(primary, "small");
        }

        return Optional.of(new AmazonProductSnapshot(
                asin,
                marketplace,
                titleValue,
                detailPageUrl,
                imageUrl
        ));
    }

    private String imageUrl(Map<?, ?> primary, String size) {
        Map<?, ?> image = nestedMap(primary, size);
        return image == null ? null : stringValue(image.get("url"));
    }

    private Map<?, ?> nestedMap(Map<?, ?> source, String key) {
        if (source == null) {
            return null;
        }
        Object value = source.get(key);
        return value instanceof Map<?, ?> map ? map : null;
    }

    private String stringValue(Object value) {
        return value instanceof String text && !text.isBlank() ? text : null;
    }
}
