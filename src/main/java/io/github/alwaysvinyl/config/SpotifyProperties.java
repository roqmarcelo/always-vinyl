package io.github.alwaysvinyl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spotify")
public class SpotifyProperties {

    private String clientId;

    private String clientSecret;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}