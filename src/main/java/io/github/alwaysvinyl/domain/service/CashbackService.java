package io.github.alwaysvinyl.domain.service;

import io.github.alwaysvinyl.domain.model.CashbackPercentage;
import io.github.alwaysvinyl.domain.model.Genre;
import io.github.alwaysvinyl.domain.repository.CashbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class CashbackService {

    @Autowired
    private CashbackRepository repository;

    public BigDecimal calculateCashback(final Genre genre, final BigDecimal value) {
        Objects.requireNonNull(genre);
        Objects.requireNonNull(value);

        Optional<CashbackPercentage> percentage = repository.findByGenreAndDayOfWeek(genre, LocalDate.now().getDayOfWeek());

        if (percentage.isPresent()) {
            return value.multiply(percentage.get().getPercentageValue());
        }

        return BigDecimal.ZERO;
    }
}