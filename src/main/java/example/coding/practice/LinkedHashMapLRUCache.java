package example.coding.practice;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapLRUCache<K,V> extends LinkedHashMap<K,V> {
	private final int capacity;
	
	public LinkedHashMapLRUCache(int capacity) {
		super(16, 0.75f, true);
		if(capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
		this.capacity = capacity;
	}
	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > capacity;
	}
	
	public int capacity() {
		return capacity;
	}
	
	public static <K,V> LinkedHashMapLRUCache<K, V> of (int capacity){
		return new LinkedHashMapLRUCache<>(capacity);
	}
	public static void main(String[] args) {
		var cache = LinkedHashMapLRUCache.<Integer, String>of(4);
		cache.put(1, "one");
		cache.put(2, "two");
		cache.put(3, "three");
		cache.get(2);
		cache.put(4, "four");
		cache.put(5, "five");
//		cache.get(2);
		System.out.println(cache);
	}

}
