package com.irtiza.todo.utils;

import java.lang.reflect.Field;

public class DtoPatcher {
    public static <T> void patchFields(T source, T target) {
        for (Field field : source.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(source);
                if (value != null) { // Only update non-null fields
                    field.set(target, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field: " + field.getName(), e);
            }
        }
    }
}
