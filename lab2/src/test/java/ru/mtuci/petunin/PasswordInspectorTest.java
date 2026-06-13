package ru.mtuci.petunin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordInspectorTest {
    private final PasswordInspector inspector = new PasswordInspector();

    @Test
    void shouldReturnZeroForEmptyPassword() {
        assertEquals(0, inspector.score(""));
    }

    @Test
    void shouldCalculateStrongPasswordScore() {
        assertEquals(5, inspector.score("Spring2026!"));
    }

    @Test
    void shouldAcceptStrongPassword() {
        assertTrue(inspector.isAcceptable("Java2026!"));
    }

    @Test
    void shouldRejectWeakPassword() {
        assertFalse(inspector.isAcceptable("qwerty"));
    }
}
