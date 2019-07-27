package io.github.alwaysvinyl.service;

import io.github.alwaysvinyl.domain.dto.AlbumDto;
import io.github.alwaysvinyl.domain.model.Album;
import io.github.alwaysvinyl.domain.model.Genre;
import io.github.alwaysvinyl.domain.repository.AlbumRepository;
import io.github.alwaysvinyl.domain.service.AlbumService;
import io.github.alwaysvinyl.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlbumServiceTest {

    @Mock
    private AlbumRepository repository;

    @InjectMocks
    private AlbumService service;

    @Before
    public void setUp() {
        final Album bornToBeBlue = new Album("Born to Be Blue", "Freddie Hubbard", BigDecimal.TEN, Genre.CLASSIC);
        final Album thriller = new Album("Thriller", "Michael Jackson", BigDecimal.ONE, Genre.POP);
        final Page<Album> allAlbums = new PageImpl<>(Arrays.asList(bornToBeBlue, thriller));

        when(repository.findById(1L)).thenReturn(Optional.of(bornToBeBlue));
        when(repository.findById(2L)).thenReturn(Optional.of(thriller));
        when(repository.findBy(Pageable.unpaged())).thenReturn(allAlbums);
        when(repository.findAllByGenre(Genre.CLASSIC, Pageable.unpaged()))
                .thenReturn(new PageImpl<>(Collections.singletonList(bornToBeBlue)));
    }

    @Test(expected = NullPointerException.class)
    public void whenNullId_thenThrowsException() {
        service.findById(null);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenInvalidId_thenThrowsException() {
        service.findById(0L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenInvalidGenre_thenThrowsException() {
        service.findPaged("FUNK", Pageable.unpaged());
    }

    @Test
    public void whenValidId_thenReturnAlbum() {
        Album album = service.findById(1L);
        assertThat(album).isNotNull();
        assertThat(album.getName()).isEqualTo("Born to Be Blue");

        album = service.findById(2L);
        assertThat(album).isNotNull();
        assertThat(album.getName()).isEqualTo("Thriller");
    }

    @Test
    public void testFindPagedAll() {
        final Page<AlbumDto> paged = service.findPaged("", Pageable.unpaged());
        assertThat(paged).isNotEmpty();
        assertThat(paged).hasSize(2);
    }

    @Test
    public void testFindAllByGenre() {
        final Page<AlbumDto> paged = service.findPaged(Genre.CLASSIC.name(), Pageable.unpaged());
        assertThat(paged).isNotEmpty();
        assertThat(paged).hasSize(1);

        AlbumDto albumDto = paged.iterator().next();
        assertThat(albumDto).isNotNull();
        assertThat(albumDto.getGenre()).isEqualTo(Genre.CLASSIC);
    }
}