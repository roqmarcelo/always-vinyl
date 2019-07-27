package io.github.alwaysvinyl.integration.service.support;

import java.math.BigDecimal;

public class PriceGenerator {

    public static final double MIN = 20.00;
    public static final double MAX = 100.00;

    private PriceGenerator() {
    }

    public static BigDecimal generate() {
        return new BigDecimal((Math.random() * (MAX - MIN)) + MIN);
    }
}