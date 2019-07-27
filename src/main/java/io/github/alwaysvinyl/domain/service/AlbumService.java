package io.github.alwaysvinyl.domain.service;

import io.github.alwaysvinyl.domain.dto.AlbumDto;
import io.github.alwaysvinyl.domain.model.Album;
import io.github.alwaysvinyl.domain.model.Genre;
import io.github.alwaysvinyl.domain.repository.AlbumRepository;
import io.github.alwaysvinyl.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository repository;

    @Transactional(readOnly = true)
    public Page<AlbumDto> findPaged(final String genre, final Pageable pageable) {
        if (StringUtils.isEmpty(genre)) {
            return repository.findBy(pageable).map(AlbumDto::of);
        }

        Optional<Genre> genreEnum = Genre.of(genre);

        if (!genreEnum.isPresent()) {
            throw new ResourceNotFoundException("Genre", "genre", genre);
        }

        return repository.findAllByGenre(genreEnum.get(), pageable).map(AlbumDto::of);
    }

    @Transactional(readOnly = true)
    public Album findById(final Long id) {
        Objects.requireNonNull(id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album", "id", id));
    }
}