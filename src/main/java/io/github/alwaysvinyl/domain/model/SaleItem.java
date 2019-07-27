package io.github.alwaysvinyl.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "SALE_ITEM")
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CASHBACK_VALUE")
    private BigDecimal cashbackValue;

    @ManyToOne
    @JoinColumn(name = "ALBUM_ID", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "SALE_ID", nullable = false)
    private Sale sale;

    public SaleItem(){}

    public SaleItem(final Album album, final BigDecimal cashbackValue) {
        this.album = album;
        this.cashbackValue = cashbackValue;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getCashbackValue() {
        return cashbackValue;
    }

    public Album getAlbum() {
        return album;
    }

    void setSale(Sale sale) {
        this.sale = sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem saleItem = (SaleItem) o;
        return Objects.equals(id, saleItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}