package io.github.alwaysvinyl.domain.dto;

import io.github.alwaysvinyl.domain.model.Album;
import io.github.alwaysvinyl.domain.model.SaleItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@ApiModel(value = "SaleItem", description = "Defines an album within a sale")
public final class SaleItemDto {

    @ApiModelProperty(value = "The generated identifier of this sale item")
    private final Long id;

    @ApiModelProperty(value = "The calculated cashback value of this sale item")
    private final BigDecimal cashbackValue;

    @ApiModelProperty(value = "The album of this sale item")
    private final AlbumDto album;

    private SaleItemDto(final Long id, final BigDecimal cashbackValue, final Album album) {
        this.id = id;
        this.cashbackValue = cashbackValue.setScale(2, RoundingMode.HALF_EVEN);
        this.album = AlbumDto.of(album);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getCashbackValue() {
        return cashbackValue;
    }

    public AlbumDto getAlbum() {
        return album;
    }

    public static SaleItemDto of(final SaleItem saleItem) {
        Objects.requireNonNull(saleItem);
        return new SaleItemDto(saleItem.getId(), saleItem.getCashbackValue(), saleItem.getAlbum());
    }
}