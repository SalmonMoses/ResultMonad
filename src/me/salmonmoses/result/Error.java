package me.salmonmoses.result;

import me.salmonmoses.result.interfaces.ThrowingFunction;

import java.util.function.Consumer;
import java.util.function.Function;

public final class Error<T, E extends Exception> implements Result<T, E> {
	private E error;

	public Error(E error) {
		this.error = error;
	}

	@Override
	public Result<T, E> then(Consumer<T> callback) {
		return this;
	}

	@Override
	public Result<T, E> onError(Consumer<E> handler) {
		handler.accept(error);
		return this;
	}

	@Override
	public <R, E2 extends Exception> Result<R, E2> map(ThrowingFunction<T, R, E2> mappingFunction) {
		return (Result<R, E2>) this;
	}

	@Override
	public <R, E2 extends Exception> Result<R, E2> flatMap(ThrowingFunction<T, Result<R, E2>, E2> mappingFunction) {
		return null;
	}

	@Override
	public <R extends Exception> Result<T, R> mapError(Function<E, Result<T, R>> mappingFunction) {
		return mappingFunction.apply(error);
	}

	@Override
	public T getValue() throws E {
		throw error;
	}

	@Override
	public boolean hasValue() {
		return false;
	}

	@Override
	public boolean hasError() {
		return true;
	}
}
