package example.coding.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveDuplicates {
	
	public static void main(String[] args) {
		List<String> names = new ArrayList<>(List.of("Alice", "Bob", "Alice", "Eve", "Bob"));
		List<String> distinctNames = removeDuplicates(names);
		System.out.println(distinctNames);
		distinctNames = removeDupliStream(names);
		System.out.println(distinctNames);
		Map<String, Long> nameWithCount = names.stream().collect(Collectors.groupingBy(name -> name, Collectors.counting()));
		nameWithCount.entrySet().stream().filter(item -> item.getValue()> 1).collect(Collectors.toList()).forEach(i -> System.out.print(i.getKey() + " "));
	}

	public static <T> List<T> removeDupliStream(List<T> list) {
		
		return list.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
	}

	public static <T> List<T> removeDuplicates(List<T> list) {
		Set<T> set = new LinkedHashSet<>(list);
		return new ArrayList<>(set);
	}
	
	
	


}
