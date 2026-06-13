package ru.mtuci.petunin.notify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicTest {
    private int value;

    @BeforeEach
    void setUp() {
        value = 20;
    }

    @Test
    void shouldCheckValue() {
        assertEquals(20, value);
        assertTrue(value > 10);
    }

    @Test
    void shouldCheckException() {
        assertThrows(ArithmeticException.class, () -> {
            int result = value / 0;
        });
    }

    @AfterEach
    void tearDown() {
        value = 0;
    }
}
