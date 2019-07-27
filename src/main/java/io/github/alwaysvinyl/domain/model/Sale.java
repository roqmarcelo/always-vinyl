package io.github.alwaysvinyl.domain.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "DATE_SOLD", nullable = false, updatable = false)
    private LocalDate dateSold;

    @Column(name = "TOTAL_CASHBACK_VALUE", precision = 12, scale = 2)
    private BigDecimal totalCashbackValue;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<SaleItem> items;

    public Long getId() {
        return id;
    }

    public LocalDate getDateSold() {
        return dateSold;
    }

    public BigDecimal getTotalCashbackValue() {
        if (totalCashbackValue == null) {
            totalCashbackValue = BigDecimal.ZERO;
        }
        return totalCashbackValue;
    }

    public void addToTotalCashbackValue(final BigDecimal cashbackValue) {
        this.totalCashbackValue = getTotalCashbackValue().add(cashbackValue);
    }

    public Collection<SaleItem> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void addItem(final SaleItem item) {
        getItems().add(item);
        item.setSale(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}