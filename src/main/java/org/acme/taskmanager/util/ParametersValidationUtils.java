package org.acme.taskmanager.util;

import java.util.Optional;

public final class ParametersValidationUtils {

    private ParametersValidationUtils() {
    }

    /**
     * Validate the given parameter is not null
     *
     * @return the passed parameter
     * @throws IllegalArgumentException with the provided error message if the validated parameter object is null
     */
    public static <T> T requireNonNull(final T possibleNull, final String errorMessage) {
        return Optional.ofNullable(possibleNull)
                       .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
