package example.coding.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TopKFrequentItemsTest {
	TopKFrequentItems obj = new TopKFrequentItems();
	@Test
	void emptyInputCase() {
		int[] nums = {};
		List<Integer> result = obj.getTopKFrequent(nums, 2);
		assertTrue(result.isEmpty());
	}

	@Test
	void nullInputCase() {
		List<Integer> result = obj.getTopKFrequent(null, 2);
		assertTrue(result.isEmpty());
	}

	@Test
	void zerokCase() {
		int[] nums = { 1, 1, 2, 2 };
		List<Integer> result = obj.getTopKFrequent(nums, 0);
		assertTrue(result.isEmpty());
	}
	
	@Test
	void singleElementInput() {
		int[] nums = {5};
		List<Integer> result = obj.getTopKFrequent(nums, 1);
		assertEquals(List.of(5), result);
	}
	
	@Test
	void kGreaterThanUniqueElements() {
		int[] nums = {1,1,2};
		List<Integer> result = obj.getTopKFrequent(nums, 5);
		assertEquals(2, result.size());
		assertTrue(result.containsAll(List.of(1,2)));
	}
	
	@Test
    void normalCase() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        List<Integer> result = obj.getTopKFrequent(nums, 2);

        assertEquals(List.of(1, 2), result);
    }
	
	@Test
    void uniformDistribution_tieBreakingApplied() {
        int[] nums = {1, 2, 3, 4};
        List<Integer> result = obj.getTopKFrequent(nums, 2);
        
        //Same frequency, smaller values preferred
        assertEquals(List.of(1, 2), result);
    }
}
