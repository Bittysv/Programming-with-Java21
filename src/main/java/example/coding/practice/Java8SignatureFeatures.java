package example.coding.practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8SignatureFeatures {
	// 2. Functional Interfaces
	@FunctionalInterface
	interface Calculator {
		int operate(int a, int b);
	}

	class CalculatorClass {
		Calculator add = (a, b) -> a + b;
		Calculator product = (a, b) -> a * b;
	}

	// 3. Stream Example
	public List<String> streamExample(List<String> namesList) {
		List<String> filteredNames = namesList.stream().filter(name -> name.startsWith("J")).sorted()
				.collect(Collectors.toList());
		return filteredNames;
	}

	public void mappingExampleOp(List<String> namesList) {
		namesList.stream().map(String::toUpperCase).collect(Collectors.toList())
				.forEach(name -> System.out.println(name));
	}

	public static void main(String[] args) {
		Java8SignatureFeatures mainObj = new Java8SignatureFeatures();

		// 1. Lambda Expressions
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
		names.forEach(name -> System.out.println(name));
		// Reverse order sorting
		Collections.sort(names, (a, b) -> b.compareTo(a));
		System.out.println("----After sorting----");
		names.forEach(name -> System.out.println(name));

		// 2. Functional Interfaces
		System.out.println("----Functional Interface Example----");
		CalculatorClass calcObj = mainObj.new CalculatorClass();
		System.out.println("Addition: " + calcObj.add.operate(15, 3));
		System.out.println("Multiplication: " + calcObj.product.operate(15, 3));

		// 3. Stream Example
		System.out.println("----Stream Example----");
		List<String> namesList = Arrays.asList("John", "Tom", "Alice", "John", "Alice", "Jane", "Jonah");
		System.out.println("----Normal Stream operation----");
		mainObj.streamExample(namesList).forEach(name -> System.out.println(name));
		System.out.println("----Stream Mapping----");
		mainObj.mappingExampleOp(namesList);
		System.out.println("----Stream Reducing----");
		List<Integer> numbers = IntStream.range(1, 11).boxed().collect(Collectors.toList());
		System.out.println("Sum: " + numbers.stream().reduce(0, Integer::sum));
		System.out.println("----Stream Parallel----");
		IntStream.range(0, 10).parallel().forEach(System.out::println);
		System.out.println("----Stream Grouping----");
		Map<String, Long> itemCount = namesList.stream()
				.collect(Collectors.groupingBy(item -> item, Collectors.counting()));
		System.out.println(itemCount);
	}

}
