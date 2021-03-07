package me.salmonmoses.result.interfaces;

@FunctionalInterface
public interface ThrowingSupplier<T, E extends Throwable> {
	T accept() throws E;
}
