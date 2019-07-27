package io.github.alwaysvinyl.domain.repository;

import io.github.alwaysvinyl.domain.model.Album;
import io.github.alwaysvinyl.domain.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Page<Album> findAllByGenre(Genre genre, Pageable pageable);

    Page<Album> findBy(Pageable pageable);
}