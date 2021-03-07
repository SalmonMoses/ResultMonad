package me.salmonmoses.result;

public class Main {
	public static void main(String[] args) {
		getString().map(s -> s + "!")
		           .then(System.out::println);
		var sad = getError().onError(e -> System.out.println(e.getMessage()));
	}

	private static Result<String, Exception> getString() {
		return Result.ok("Hello, World");
	}

	private static Result<String, Exception> getError() {
		return Result.error(new Exception("Saaaaaad"));
	}
}
