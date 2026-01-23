package com.example.probnik;

import java.util.Objects;
import java.util.function.Consumer;

public class UpdateUtils {

    private static void fieldUpdate(String oldValue, String newValue, Consumer<String> action) {
        if (oldValue != null && newValue != null) {
            if (Objects.equals(oldValue.trim(), newValue.trim())) {
                action.accept(oldValue);
            }
        }
    }

    private static <T> void fieldUpdate(T oldValue, T newValue, Consumer<T> action) {
        if (oldValue != null && newValue != null) {
            if (Objects.equals(oldValue, newValue)) {
                action.accept(oldValue);
            }
        }
    }

    public static boolean updateObject(Object obj, Object updateObj, Consumer<String> action) {
        boolean changed = false;
        return changed;
    }
}
