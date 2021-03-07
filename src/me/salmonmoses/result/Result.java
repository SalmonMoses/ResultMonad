package me.salmonmoses.result;

import me.salmonmoses.result.interfaces.ThrowingFunction;

import java.util.function.Consumer;
import java.util.function.Function;

public sealed interface Result<T, E extends Exception> permits Ok, Error {
	static <T, E extends Exception> Ok<T, E> ok(T value) {
		return new Ok<T, E>(value);
	}

	static <T, E extends Exception> Error<T, E> error(E error) {
		return new Error<T, E>(error);
	}

	Result<T, E> then(Consumer<T> callback);

	Result<T, E> onError(Consumer<E> handler);

	<R, E2 extends Exception> Result<R, E2> map(ThrowingFunction<T, R, E2> mappingFunction);

	<R, E2 extends Exception> Result<R, E2> flatMap(ThrowingFunction<T, Result<R, E2>, E2> mappingFunction);

	<R extends Exception> Result<T, R> mapError(Function<E, Result<T, R>> mappingFunction);

	T getValue() throws E;

	boolean hasValue();

	boolean hasError();
}
