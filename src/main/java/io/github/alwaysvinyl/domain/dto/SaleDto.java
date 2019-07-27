package io.github.alwaysvinyl.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.alwaysvinyl.domain.model.Sale;
import io.github.alwaysvinyl.domain.model.SaleItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@ApiModel(value = "Sale", description = "Defines a sale by items")
public final class SaleDto {

    @ApiModelProperty(value = "The generated identifier of this sale")
    private final Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @ApiModelProperty(value = "The date of the sale")
    private final LocalDate dateSold;

    @ApiModelProperty(value = "The total calculated cashback os this sale")
    private final BigDecimal totalCashbackValue;

    @ApiModelProperty(value = "List of items of the sale")
    private final Collection<SaleItemDto> items;

    private SaleDto(final Long id, final LocalDate dateSold, final BigDecimal totalCashbackValue, final Collection<SaleItem> items) {
        this.id = id;
        this.dateSold = dateSold;
        this.totalCashbackValue = totalCashbackValue.setScale(2, RoundingMode.HALF_EVEN);
        this.items = items.stream().map(SaleItemDto::of).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateSold() {
        return dateSold;
    }

    public BigDecimal getTotalCashbackValue() {
        return totalCashbackValue;
    }

    public Collection<SaleItemDto> getItems() {
        return items;
    }

    public static SaleDto of(final Sale sale) {
        Objects.requireNonNull(sale);
        return new SaleDto(sale.getId(), sale.getDateSold(), sale.getTotalCashbackValue(), sale.getItems());
    }
}