package io.github.alwaysvinyl.domain;

import io.github.alwaysvinyl.domain.model.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class GenreTest {

    @Test
    public void whenValidGenre_thenGenreShouldBeFound() {
        assertThat(Genre.of("POP")).isNotEmpty();
        assertThat(Genre.of("MPB")).isNotEmpty();
        assertThat(Genre.of("CLASSIC")).isNotEmpty();
        assertThat(Genre.of("ROCK")).isNotEmpty();
    }

    @Test
    public void whenInvalidGenre_thenGenreShouldNotBeFound() {
        assertThat(Genre.of("")).isEmpty();
        assertThat(Genre.of("FUNK")).isEmpty();
    }
}