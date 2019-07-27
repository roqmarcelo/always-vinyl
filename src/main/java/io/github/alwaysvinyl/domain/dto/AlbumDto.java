package io.github.alwaysvinyl.domain.dto;

import io.github.alwaysvinyl.domain.model.Album;
import io.github.alwaysvinyl.domain.model.Genre;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;

@ApiModel(value = "Album", description = "Describes an album by name, artist, price and genre")
public final class AlbumDto {

    @ApiModelProperty(value = "The generated identifier of this album")
    private final Long id;

    @ApiModelProperty(value = "The album name")
    private final String name;

    @ApiModelProperty(value = "The album artist")
    private final String artist;

    @ApiModelProperty(value = "The album price")
    private final BigDecimal price;

    @ApiModelProperty(value = "The album genre")
    private final Genre genre;

    private AlbumDto(final Long id, final String name, final String artist, final BigDecimal price, final Genre genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
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

    public static AlbumDto of(final Album album) {
        return new AlbumDto(album.getId(), album.getName(), album.getArtist(), album.getPrice(), album.getGenre());
    }
}