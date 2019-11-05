package com.Kh033Java.travelbook.validation;

import com.Kh033Java.travelbook.exception.NotFoundException;

import java.util.Optional;

public class ValidationUtil {

    private static String template = "%s with such id [%s] not found";
    private static String secondTemplate = "Such %s not found";

    public static <T> T checkBeforeGet(final Optional<T> obj, final Long id, final Class clazz) {
        if (!obj.isPresent()) {
            throw new NotFoundException(String.format(template, clazz.getSimpleName(), id));
        }
        return obj.get();
    }

    public static void checkIfValid(final boolean isValid, final Long id, final Class clazz) {
        if (!isValid) {
            throw new NotFoundException(String.format(template, clazz.getSimpleName(), id));
        }
    }

    public static <T> T checkBeforeGet(final Optional<T> obj, final Class clazz) {
        if (obj.isEmpty()) {
            throw new NotFoundException(String.format(secondTemplate, clazz.getSimpleName()));
        }
        return obj.get();
    }
}
