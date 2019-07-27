package io.github.alwaysvinyl.integration.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import io.github.alwaysvinyl.domain.model.Album;
import io.github.alwaysvinyl.domain.model.Genre;
import io.github.alwaysvinyl.domain.repository.AlbumRepository;
import io.github.alwaysvinyl.integration.service.support.PriceGenerator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SpotifyIntegrationService {

    @Autowired
    private Logger logger;

    @Autowired
    private SpotifyApi spotifyApi;

    @Autowired
    private AlbumRepository albumRepository;

    public void execute() {
        if (albumRepository.count() == 0) {
            insertAlbums();
            logger.info("Albums fetched successfully from Spotify.");
        } else {
            logger.info("Skipped fetch albums because albums were already fetched.");
        }
    }

    private void insertAlbums() {
        Stream.of(Genre.values()).forEach(genre -> {
            Stream<AlbumSimplified> albumsByGenre = searchAlbumBy(genre);

            List<Album> albums = albumsByGenre.map(albumSimplified -> convert(albumSimplified, genre))
                    .collect(Collectors.toList());

            albumRepository.saveAll(albums);
        });
    }

    private Stream<AlbumSimplified> searchAlbumBy(final Genre genre) {
        try {
            AlbumSimplified[] albums = spotifyApi.searchAlbums(genre.name())
                    .limit(50)
                    .build()
                    .execute()
                    .getItems();

            return Stream.of(albums);
        } catch (IOException | SpotifyWebApiException e) {
            logger.error("Error while fetching albums from Spotify", e);
        }

        return Stream.empty();
    }

    private Album convert(final AlbumSimplified albumSimplified, final Genre genre) {
        final ArtistSimplified[] artists = albumSimplified.getArtists();
        final String artist;

        if (artists == null || artists.length == 0) {
            artist = "Unknown";
        } else {
            artist = artists[0].getName();
        }

        return new Album(albumSimplified.getName(), artist, PriceGenerator.generate(), genre);
    }
}