package id.co.libralink.common.util;

import id.co.libralink.common.base.code.ErrorCode;
import id.co.libralink.common.exception.BaseException;
import id.co.libralink.common.exception.DataNotFoundException;

import java.util.Optional;

public final class PreconditionUtil {

    private PreconditionUtil() {
        // can't instantiate
    }

    public static <T> T assertNotNull(T reference, Class<T> clazz) {
        if (reference == null) {
            throw new DataNotFoundException(clazz);
        }
        return reference;
    }

    public static <T> T assertNotNull(T reference, Class<T> clazz, String param) {
        if (reference == null) {
            throw new DataNotFoundException(clazz, param);
        }
        return reference;
    }

    public static <T> T assertNotNull(T reference, String message) {
        if (reference == null) {
            throw new BaseException(message);
        }
        return reference;
    }

    public static <T> T assertNotNull(T reference, RuntimeException exception) {
        if (reference == null) {
            throw exception;
        }
        return reference;
    }

    public static <T> T assertNotNull(Optional<T> reference, Class<T> clazz) {
        if (reference.isEmpty()) {
            throw new DataNotFoundException(clazz);
        }
        return reference.get();
    }

    public static <T> T assertNotNull(Optional<T> reference, Class<T> clazz, String param) {
        if (reference.isEmpty()) {
            throw new DataNotFoundException(clazz, param);
        }
        return reference.get();
    }

    public static <T> T assertNotNull(Optional<T> reference, String message) {
        if (reference.isEmpty()) {
            throw new BaseException(message);
        }
        return reference.get();
    }

    public static <T> T assertNotNull(Optional<T> reference, RuntimeException exception) {
        if (reference.isEmpty()) {
            throw exception;
        }
        return reference.get();
    }

    public static void assertTrue(boolean expression, String message) {
        if (!expression) {
            throw new BaseException(message);
        }
    }

    public static void assertTrue(boolean expression, ErrorCode errorCode, Object... args) {
        if (!expression) {
            throw new BaseException(errorCode, args);
        }
    }

    public static void assertTrue(boolean expression, RuntimeException exception) {
        if (!expression) {
            throw exception;
        }
    }

    public static void assertFalse(boolean expression, String message) {
        if (!expression) {
            throw new BaseException(message);
        }
    }

    public static void assertFalse(boolean expression, ErrorCode errorCode, Object... args) {
        if (!expression) {
            throw new BaseException(errorCode, args);
        }
    }

    public static void assertFalse(boolean expression, RuntimeException exception) {
        if (!expression) {
            throw exception;
        }
    }

    public static <T> void assertEqual(T reference, T target, String message) {
        if (!reference.equals(target)) {
            throw new BaseException(message);
        }
    }

    public static <T> void assertEqual(T reference, T target, ErrorCode errorCode, Object... args) {
        if (!reference.equals(target)) {
            throw new BaseException(errorCode, args);
        }
    }

    public static <T> void assertEqual(T reference, T target, RuntimeException exception) {
        if (!reference.equals(target)) {
            throw exception;
        }
    }

    public static <T> void assertEqual(Comparable<T> reference, T target, String message) {
        if (reference.compareTo(target) != 0) {
            throw new BaseException(message);
        }
    }

    public static <T> void assertEqual(Comparable<T> reference, T target, ErrorCode errorCode, Object... args) {
        if (reference.compareTo(target) != 0) {
            throw new BaseException(errorCode, args);
        }
    }

    public static <T> void assertEqual(Comparable<T> reference, T target, RuntimeException exception) {
        if (reference.compareTo(target) != 0) {
            throw exception;
        }
    }

    public static <T> void assertNotEqual(T reference, T target, String message) {
        if (reference.equals(target)) {
            throw new BaseException(message);
        }
    }

    public static <T> void assertNotEqual(T reference, T target, ErrorCode errorCode, Object... args) {
        if (reference.equals(target)) {
            throw new BaseException(errorCode, args);
        }
    }

    public static <T> void assertNotEqual(T reference, T target, RuntimeException exception) {
        if (reference.equals(target)) {
            throw exception;
        }
    }

    public static <T> void assertNotEqual(Comparable<T> reference, T target, String message) {
        if (reference.compareTo(target) == 0) {
            throw new BaseException(message);
        }
    }

    public static <T> void assertNotEqual(Comparable<T> reference, T target, ErrorCode errorCode, Object... args) {
        if (reference.compareTo(target) == 0) {
            throw new BaseException(errorCode, args);
        }
    }

    public static <T> void assertNotEqual(Comparable<T> reference, T target, RuntimeException exception) {
        if (reference.compareTo(target) == 0) {
            throw exception;
        }
    }

}
