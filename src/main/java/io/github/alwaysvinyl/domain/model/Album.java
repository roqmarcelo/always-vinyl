package io.github.alwaysvinyl.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String artist;

    @Column(precision = 8, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Album(){}

    public Album(final String name, final String artist, final BigDecimal price, final Genre genre) {
        this.name = name;
        this.artist = artist;
        this.price = price == null ? BigDecimal.ZERO : price;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}