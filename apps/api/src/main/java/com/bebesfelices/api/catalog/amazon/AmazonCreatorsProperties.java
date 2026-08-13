package com.bebesfelices.api.catalog.amazon;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "amazon.creators")
public class AmazonCreatorsProperties {

    private String credentialId = "";
    private String credentialSecret = "";
    private String credentialVersion = "3.2";
    private String partnerTag = "";
    private String marketplace = "www.amazon.es";
    private URI apiBaseUrl = URI.create("https://creatorsapi.amazon");
    private Duration productCacheTtl = Duration.ofHours(1);
    private Map<String, String> productAsins = new LinkedHashMap<>();

    public boolean isConfigured() {
        return hasText(credentialId) && hasText(credentialSecret) && hasText(partnerTag);
    }

    public URI tokenEndpoint() {
        return switch (credentialVersion) {
            case "3.1" -> URI.create("https://api.amazon.com/auth/o2/token");
            case "3.2" -> URI.create("https://api.amazon.co.uk/auth/o2/token");
            case "3.3" -> URI.create("https://api.amazon.co.jp/auth/o2/token");
            default -> throw new IllegalStateException(
                    "Versión de credenciales de Amazon Creators no soportada: " + credentialVersion);
        };
    }

    public String asinFor(String productId) {
        String asin = productAsins.get(productId);
        return hasText(asin) ? asin.trim() : null;
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getCredentialSecret() {
        return credentialSecret;
    }

    public void setCredentialSecret(String credentialSecret) {
        this.credentialSecret = credentialSecret;
    }

    public String getCredentialVersion() {
        return credentialVersion;
    }

    public void setCredentialVersion(String credentialVersion) {
        this.credentialVersion = credentialVersion;
    }

    public String getPartnerTag() {
        return partnerTag;
    }

    public void setPartnerTag(String partnerTag) {
        this.partnerTag = partnerTag;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace;
    }

    public URI getApiBaseUrl() {
        return apiBaseUrl;
    }

    public void setApiBaseUrl(URI apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    public Duration getProductCacheTtl() {
        return productCacheTtl;
    }

    public void setProductCacheTtl(Duration productCacheTtl) {
        this.productCacheTtl = productCacheTtl;
    }

    public Map<String, String> getProductAsins() {
        return productAsins;
    }

    public void setProductAsins(Map<String, String> productAsins) {
        this.productAsins = productAsins;
    }
}
