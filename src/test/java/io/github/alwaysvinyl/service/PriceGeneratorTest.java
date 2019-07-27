package io.github.alwaysvinyl.service;

import io.github.alwaysvinyl.integration.service.support.PriceGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PriceGeneratorTest {

    @Test
    public void isGeneratedPriceInRange() {
        final BigDecimal generatedPrice = PriceGenerator.generate();
        assertThat(generatedPrice).isGreaterThanOrEqualTo(BigDecimal.valueOf(PriceGenerator.MIN));
        assertThat(generatedPrice).isLessThanOrEqualTo(BigDecimal.valueOf(PriceGenerator.MAX));
    }
}