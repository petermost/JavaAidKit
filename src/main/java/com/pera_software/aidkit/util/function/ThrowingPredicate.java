package com.pera_software.aidkit.util.function;

@FunctionalInterface
public interface ThrowingPredicate<T> {
    boolean test(T t) throws Exception;
}
