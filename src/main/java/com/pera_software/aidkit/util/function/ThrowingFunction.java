package com.pera_software.aidkit.util.function;

@FunctionalInterface
public interface ThrowingFunction {
	int run() throws Exception;
}
