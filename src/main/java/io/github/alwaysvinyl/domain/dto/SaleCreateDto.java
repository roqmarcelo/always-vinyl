package io.github.alwaysvinyl.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;

@ApiModel(value = "SaleCreate", description = "Defines a sale to be made")
public final class SaleCreateDto {

    @NotEmpty
    @ApiModelProperty(value = "List of items of the sale to be made")
    private Collection<SaleItemCreateDto> items;

    public Collection<SaleItemCreateDto> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }
}