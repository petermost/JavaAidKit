package com.pera_software.aidkit.util.function;

@FunctionalInterface
public interface ThrowingSupplier<T> {
	T get() throws Exception;
}
