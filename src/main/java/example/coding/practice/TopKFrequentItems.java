package example.coding.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopKFrequentItems {

	private static final Logger log = LoggerFactory.getLogger(TopKFrequentItems.class);

	public List<Integer> getTopKFrequent(int[] nums, int k) {
		log.info("Starting getTopKFrequent with k = {} and input size = {}", k, nums == null ? 0 : nums.length);

		if (nums == null || nums.length == 0 || k < 0) {
			log.warn("Empty input or invalid k. Returning empty list.");
			return Collections.emptyList();
		}

		// Frequency Map
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}
		log.debug("Frequency map created with {} unique elements", frequencyMap.size());
		PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> {
			if (!a.getValue().equals(b.getValue())) {
				return a.getValue() - b.getValue();
			}
			return b.getKey() - a.getKey();
		});

		for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
			minHeap.offer(entry);
			log.debug("Heap offer: value = {} frequency = {}", entry.getKey(), entry.getValue());
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}

		List<Integer> result = new ArrayList<>();
		while (!minHeap.isEmpty()) {
			result.add(minHeap.poll().getKey());
		}
		Collections.sort(result);
		log.info("Top-{} frequent elements result={}", k, result);
		return result;
	}

	public static void main(String[] args) {
		TopKFrequentItems obj = new TopKFrequentItems();
//		log.info("Is DEBUG enabled? {}", log.isDebugEnabled());
		System.out.println("Top K Frequent Elements: "
				+ obj.getTopKFrequent(new int[] { 1, 2, 1, 3, 2, 1, 3, 4, 1, 3, 1, 2, 1, 5, 5, 5, 5 }, 2));
	}

}
