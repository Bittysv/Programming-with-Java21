package example.coding.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueueLock<T> {

	private final Queue<T> queue = new LinkedList<>();
	private final int capacity;
	
	private final ReentrantLock lock;
	private final Condition notFull;
	private final Condition notEmpty;
	
	public BoundedBlockingQueueLock(int capacity, boolean fair) {
		if(capacity<0 ) {
			throw new IllegalArgumentException("Queue capacity must > 0");
		}
		this.capacity = capacity;
		this.lock = new ReentrantLock(fair);
		this.notFull = lock.newCondition();
		this.notEmpty = lock.newCondition();
	}
	
	public void put(T item) throws InterruptedException {
		lock.lock();
		try {
			while(queue.size() == capacity) {
				notFull.await();
			}
			queue.add(item);
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public T take() throws InterruptedException{
		lock.lock();
		try {
			while (queue.isEmpty()) {
				notEmpty.await();				
			}
			T item = queue.poll();
			notFull.signal();
			return item;
		} finally {
			lock.unlock();
		}
	}
}
