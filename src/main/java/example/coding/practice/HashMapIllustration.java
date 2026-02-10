package example.coding.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapIllustration {

	public static void main(String[] args) {
		Map<Integer, String> hashMapExample = new HashMap<>();
		Map<Integer, String> conHashmap = new ConcurrentHashMap<>();

		Runnable task = () -> {
			for (int i = 0; i < 1000; i++) {
				hashMapExample.put(i, Thread.currentThread().getName());
			}
		};

		Runnable conTask = () -> {
			for (int i = 0; i < 1000; i++) {
				conHashmap.put(i, Thread.currentThread().getName());
			}
		};

		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);

		Thread t3 = new Thread(conTask);
		Thread t4 = new Thread(conTask);

		t1.start();
		t2.start();

		t3.start();
		t4.start();
		;

		try {
			t1.join();
			t2.join();

			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("HashMap size: " + hashMapExample.size()); // Each run gives different size because multiple
																		// threads modify its internal structure without
																		// synchronization
		System.out.println("ConcurrentHashMap size: " + conHashmap.size()); // It uses CAS and fine-grained locking, so
																			// size remains correct
	}

}
