package io.github.alwaysvinyl.domain;

import io.github.alwaysvinyl.domain.model.Sale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SaleTest {

    @Test
    public void checkTotalCashbackValueIsCorrectlyInitialized() {
        final Sale sale = new Sale();
        assertThat(sale.getTotalCashbackValue()).isNotNull();
        assertThat(sale.getTotalCashbackValue()).isZero();
    }

    @Test
    public void checkTotalCashbackValueCalculation() {
        final Sale sale = new Sale();
        sale.addToTotalCashbackValue(BigDecimal.TEN);
        assertThat(sale.getTotalCashbackValue()).isEqualByComparingTo(BigDecimal.TEN);

        sale.addToTotalCashbackValue(BigDecimal.valueOf(100));
        assertThat(sale.getTotalCashbackValue()).isEqualByComparingTo(BigDecimal.valueOf(110));
    }
}