package io.github.alwaysvinyl.domain.model;

import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

public enum Genre {
    POP,
    MPB,
    CLASSIC,
    ROCK;

    public static Optional<Genre> of(String value) {
        if (StringUtils.isEmpty(value)) {
            return Optional.empty();
        }

        return Stream.of(values()).filter(genre -> genre.name().equalsIgnoreCase(value)).findFirst();
    }
}