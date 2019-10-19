package com.Kh033Java.travelbook.util;


import com.Kh033Java.travelbook.util.exception.NotFoundException;

import java.util.Optional;

/**
 * Contains util methods for purposes of validation entity objects.
 */
public class ValidationUtil {
    /**
     * Error message template.
     */
    private static String template = "%s with such id [%s] not found";

    /**
     * If obj parameter is not present, then throws NotFoundException.
     *
     * @param obj   entity object
     * @param id    entity id
     * @param clazz Class instance of entity object
     * @param <T>   Type of entity
     * @return entity object retrieved from Optional container.
     */
    public static <T> T checkBeforeGet(final Optional<T> obj, final Long id, final Class clazz) {
        if (obj.isEmpty()) {
            throw new NotFoundException(String.format(template, clazz.getSimpleName(), id));
        }
        return obj.get();
    }

    /**
     * If isValid parameter is false, then throws NotFoundException.
     *
     * @param isValid condition for validation purposes
     * @param id      entity id
     * @param clazz   Class instance of entity object
     */
    public static void checkIfValid(final boolean isValid, final Long id, final Class clazz) {
        if (!isValid) {
            throw new NotFoundException(String.format(template, clazz.getSimpleName(), id));
        }
    }

}
