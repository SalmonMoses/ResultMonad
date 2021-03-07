package me.salmonmoses.result;

import me.salmonmoses.result.interfaces.ThrowingFunction;

import java.util.function.Consumer;
import java.util.function.Function;

public final class Ok<T, E extends Exception> implements Result<T, E> {
	private T value;

	public Ok(T value) {
		this.value = value;
	}

	@Override
	public Result<T, E> then(Consumer<T> callback) {
		callback.accept(value);
		return this;
	}

	@Override
	public Result<T, E> onError(Consumer<E> handler) {
		return this;
	}

	@Override
	public <R, E2 extends Exception> Result<R, E2> map(ThrowingFunction<T, R, E2> mappingFunction) {
		try {
			return Result.ok(mappingFunction.apply(value));
		} catch (Exception e) {
			return (Result<R, E2>) Result.error(e);
		}
	}

	@Override
	public <R, E2 extends Exception> Result<R, E2> flatMap(ThrowingFunction<T, Result<R, E2>, E2> mappingFunction) {
		try {
			return mappingFunction.apply(value);
		} catch (Exception e) {
			return (Result<R, E2>) Result.error(e);
		}
	}

	@Override
	public <R extends Exception> Result<T, R> mapError(Function<E, Result<T, R>> mappingFunction) {
		return (Result<T, R>) this;
	}

	@Override
	public T getValue() throws E {
		return value;
	}

	@Override
	public boolean hasValue() {
		return true;
	}

	@Override
	public boolean hasError() {
		return false;
	}
}
