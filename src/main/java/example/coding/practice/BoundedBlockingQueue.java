package example.coding.practice;

import java.util.LinkedList;
import java.util.Queue;

//Implementation with wait() and notifyAll()
public class BoundedBlockingQueue<T> {

	private final Queue<T> queue = new LinkedList<>();
	private final int capacity;

	public BoundedBlockingQueue(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity must be > 0");
		}
		this.capacity = capacity;
	}

	public synchronized void put(T item) throws InterruptedException {
		while (queue.size() == capacity) {
			wait();
		}
		queue.add(item);
		notifyAll();
	}

	public synchronized T take() throws InterruptedException {
		while (queue.isEmpty()) {
			wait();
		}
		T item = queue.poll();
		notifyAll();
		return item;
	}

	public synchronized int size() {
		return queue.size();
	}

}
