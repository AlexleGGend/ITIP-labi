package ru.mtuci.petunin;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CartCalculatorTest {
    private final CartCalculator calculator = new CartCalculator();

    @Test
    void shouldCalculateTotal() {
        assertEquals(600.0, calculator.total(List.of(100.0, 200.0, 300.0)));
    }

    @Test
    void shouldApplyDiscount() {
        assertEquals(900.0, calculator.discountedTotal(List.of(500.0, 500.0), 10));
    }

    @Test
    void shouldCalculateAverage() {
        assertEquals(25.0, calculator.average(List.of(10.0, 20.0, 45.0)));
    }

    @Test
    void shouldThrowForWrongDiscount() {
        assertThrows(IllegalArgumentException.class, () -> calculator.discountedTotal(List.of(100.0), 150));
    }
}
