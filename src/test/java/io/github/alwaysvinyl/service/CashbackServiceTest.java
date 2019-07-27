package io.github.alwaysvinyl.service;

import io.github.alwaysvinyl.domain.model.CashbackPercentage;
import io.github.alwaysvinyl.domain.model.Genre;
import io.github.alwaysvinyl.domain.repository.CashbackRepository;
import io.github.alwaysvinyl.domain.service.CashbackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CashbackServiceTest {

    @Mock
    private CashbackRepository repository;

    @InjectMocks
    private CashbackService service;

    @Before
    public void setUp() {
        final DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        final CashbackPercentage cashbackPercentage = new CashbackPercentage(Genre.CLASSIC, dayOfWeek, BigDecimal.valueOf(0.1));

        when(repository.findByGenreAndDayOfWeek(Genre.CLASSIC, dayOfWeek)).thenReturn(Optional.of(cashbackPercentage));
    }

    @Test(expected = NullPointerException.class)
    public void whenNullGenre_thenThrowsException() {
        service.calculateCashback(null, BigDecimal.ZERO);
    }

    @Test(expected = NullPointerException.class)
    public void whenNullValue_thenThrowsException() {
        service.calculateCashback(Genre.CLASSIC, null);
    }

    @Test
    public void whenInvalidGenre_thenReturnZero() {
        BigDecimal cashback = service.calculateCashback(Genre.ROCK, BigDecimal.TEN);
        assertThat(cashback).isZero();

        cashback = service.calculateCashback(Genre.MPB, BigDecimal.ONE);
        assertThat(cashback).isZero();

        cashback = service.calculateCashback(Genre.POP, BigDecimal.ONE);
        assertThat(cashback).isZero();
    }

    @Test
    public void whenValidGenre_thenReturnCashback() {
        BigDecimal cashback = service.calculateCashback(Genre.CLASSIC, BigDecimal.TEN);
        assertThat(cashback).isEqualByComparingTo(BigDecimal.ONE);

        cashback = service.calculateCashback(Genre.CLASSIC, BigDecimal.valueOf(100));
        assertThat(cashback).isEqualByComparingTo(BigDecimal.TEN);
    }
}