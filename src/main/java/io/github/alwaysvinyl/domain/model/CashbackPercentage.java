package io.github.alwaysvinyl.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.Objects;

@Entity
@Table(name = "CASHBACK_PERCENTAGE")
public class CashbackPercentage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "DAY_OF_WEEK")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "PERCENTAGE_VALUE",precision = 5, scale = 2)
    private BigDecimal percentageValue;

    public CashbackPercentage() {}

    public CashbackPercentage(final Genre genre, final DayOfWeek dayOfWeek, final BigDecimal percentageValue) {
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.percentageValue = percentageValue;
    }

    public Long getId() {
        return id;
    }

    public Genre getGenre() {
        return genre;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public BigDecimal getPercentageValue() {
        return percentageValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashbackPercentage that = (CashbackPercentage) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}