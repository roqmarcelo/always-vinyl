package io.github.alwaysvinyl.config;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SpotifyConfiguration {

    @Autowired
    private Logger logger;

    @Autowired
    private SpotifyProperties properties;

    @Bean
    public SpotifyApi spotifyApi() {
        SpotifyApi spotifyApi = SpotifyApi.builder()
                .setClientId(properties.getClientId())
                .setClientSecret(properties.getClientSecret())
                .build();

        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

        try {
            spotifyApi.setAccessToken(getAccessToken(clientCredentialsRequest));
        } catch (IOException | SpotifyWebApiException e) {
            logger.error("Error while trying to retrieve the access token from Spotify Api.", e);
        }

        return spotifyApi;
    }

    private String getAccessToken(final ClientCredentialsRequest clientCredentialsRequest)
            throws IOException, SpotifyWebApiException {
        return clientCredentialsRequest.execute().getAccessToken();
    }
}