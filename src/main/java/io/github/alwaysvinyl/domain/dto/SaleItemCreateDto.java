package io.github.alwaysvinyl.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel(value = "SaleItemCreate", description = "Defines an album within a sale")
public final class SaleItemCreateDto {

    @Min(1)
    @NotNull
    @ApiModelProperty(value = "The album identifier")
    private Long albumId;

    public SaleItemCreateDto() {}

    public SaleItemCreateDto(final Long albumId) {
        this.albumId = albumId;
    }

    public Long getAlbumId() {
        return albumId;
    }
}