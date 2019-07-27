package io.github.alwaysvinyl;

import io.github.alwaysvinyl.config.SpotifyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({SpotifyProperties.class})
public class AlwaysVinylApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlwaysVinylApplication.class, args);
    }
}