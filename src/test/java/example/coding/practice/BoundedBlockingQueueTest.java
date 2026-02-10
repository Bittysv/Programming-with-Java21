package example.coding.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

public class BoundedBlockingQueueTest {
	@Test
	void multipleProducersConsumers() throws Exception {
		BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<Integer>(5);

		int producers = 3;
		int consumers = 3;
		int itemsPerProducer = 10;

		ExecutorService executor = Executors.newFixedThreadPool(6);
		AtomicInteger consumed = new AtomicInteger();

		// producers
		for (int p = 0; p < producers; p++) {
			executor.submit(() -> {
				try {
					for (int i = 0; i < itemsPerProducer; i++) {
						queue.put(i);
					}
				} catch (InterruptedException e) {
				}
			});
		}

		// consumers
		for (int c = 0; c < consumers; c++) {
			executor.submit(() -> {
				try {
					for (int i = 0; i < itemsPerProducer; i++) {
						queue.take();
						consumed.incrementAndGet();

					}
				} catch (InterruptedException e) {
				}
			});
		}

		executor.shutdown();
		executor.awaitTermination(5, TimeUnit.SECONDS);
//		System.out.println(consumed.get());
		assertEquals(producers * itemsPerProducer, consumed.get());
	}

}
