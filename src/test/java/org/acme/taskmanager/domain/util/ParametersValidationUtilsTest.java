package org.acme.taskmanager.domain.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.acme.taskmanager.domain.util.ParametersValidationUtils.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParametersValidationUtilsTest {

    @Nested
    class RequireNonNull {

        @Test
        void requireNonNull_whenNotNullThenValidated() {
            final var notNullParam = new Object();

            final Object validated = requireNonNull(notNullParam, "");

            assertSame(notNullParam, validated);
        }

        @Test
        void requireNonNull_whenNullThenException() {
            assertThrows(
              IllegalArgumentException.class,
              () -> requireNonNull(null, "Parameter 'whatever' cannot be null"));
        }
    }
}