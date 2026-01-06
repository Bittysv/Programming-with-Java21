package example.coding.practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorMap {
	public static List<String> getErrorCodes(int k, int t, List<Integer> timestamps, List<String> errorCodes) {
		int n = timestamps.size();
		if (n == 0 || k <= 0 || t <= 0)
			return Collections.emptyList();

		Map<String, Integer> errorMap = new HashMap<>();
		Deque<Integer> window = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
			String code = errorCodes.get(i);
			int currentTime = timestamps.get(i);
			// Add current error code
			errorMap.put(code, errorMap.getOrDefault(code, 0) + 1);
			window.addLast(i);

			// Remove old timestamps outside t-second window
			while (!window.isEmpty() && currentTime - timestamps.get(window.peekFirst()) >= t) {
				int oldIndex = window.pollFirst();
				String oldCode = errorCodes.get(oldIndex);
				errorMap.put(oldCode, errorMap.get(oldCode) - 1);
				if (errorMap.get(oldCode) == 0) {
					errorMap.remove(oldCode);
				}
			}
		}

		//Collect codes with frequency >= k
		List<String> result = new ArrayList<>();
		for(Map.Entry<String, Integer> entry: errorMap.entrySet()) {
			if(entry.getValue()>= k) {
				result.add(entry.getKey());
			}
		}
		Collections.sort(result);
		return result;

	}
	
	public static void main(String[] args) {
		int k=2, t=10;
		List<Integer> timestamps = Arrays.asList(100, 101, 102, 105, 110);
        List<String> errorCodes = Arrays.asList("e1", "e2", "e1", "e1", "e2");
        
        System.out.println(getErrorCodes(k, t, timestamps, errorCodes));
	}

}
