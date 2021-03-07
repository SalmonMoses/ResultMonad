package me.salmonmoses.result;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		getString().map(s -> s + "!")
		           .then(System.out::println);

		getError().onError(e -> System.out.println(e.getMessage()));

		getRandomError().then(System.out::println)
		                .onError(e -> System.out.println(e.getMessage()));
	}

	private static Result<String, Exception> getString() {
		return Result.ok("Hello, World");
	}

	private static Result<String, Exception> getError() {
		return Result.error(new Exception("Saaaaaad"));
	}

	private static Result<String, Exception> getRandomError() {
		Random r = new Random();
		return r.nextBoolean() ? Result.ok("Value") : Result.error(new Exception("Error"));
	}
}
