package ru.shlanlinec.graduation.mealservice.util;

import org.springframework.lang.Nullable;

public class Util {
    private Util() {
    }

    public static <T extends Comparable<? super T>> boolean isBetweenInclusive(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) <= 0);
    }
}
