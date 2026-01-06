package example.coding.practice;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DetectServiceId {

	public static List<String> detectServiceTimeouts(List<Integer> timestamp, List<String> serviceId, int threshold) {
		Map<String, List<Integer>> serviceMap = new HashMap<>();

		// Group timestamps by service ID
		for (int i = 0; i < serviceId.size(); i++) {
			serviceMap.computeIfAbsent(serviceId.get(i), k -> new ArrayList<>()).add(timestamp.get(i));
		}

		Set<String> timedOutServices = new HashSet<>();

		// Check each service for timeout
		for (Map.Entry<String, List<Integer>> entry : serviceMap.entrySet()) {
			List<Integer> times = entry.getValue();
			Collections.sort(times);

			for (int i = 1; i < times.size(); i++) {
				if ((long) times.get(i) - times.get(i - 1) > threshold) {
					timedOutServices.add(entry.getKey());
					break; // No need to check further for this service ID
				}
			}
		}

		// sort the array in lexicographical order
		List<String> result = new ArrayList<>(timedOutServices);
		Collections.sort(result);

		return result;
	}

	public static void main(String[] args) {
		// Example 1
		List<Integer> timestamps1 = Arrays.asList(10, 20, 80, 10, 55);
		List<String> services1 = Arrays.asList("svc1", "svc1", "svc1", "svc2", "svc2");
		int threshold1 = 30;
		System.out.println(detectServiceTimeouts(timestamps1, services1, threshold1));

		// Example 2
		List<Integer> timestamps2 = Arrays.asList(1, 2, 3);
		List<String> services2 = Arrays.asList("svc1", "svc2", "svc1");
		int threshold2 = 1;
		System.out.println(detectServiceTimeouts(timestamps2, services2, threshold2));
	}

}
