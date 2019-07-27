package io.github.alwaysvinyl.integration.listener;

import io.github.alwaysvinyl.integration.service.SpotifyIntegrationService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Logger logger;

    @Autowired
    private SpotifyIntegrationService spotifyIntegrationService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.info("Application Ready! Trying to fetch albums from Spotify");
        spotifyIntegrationService.execute();
    }
}