package example.coding.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

public class BoundedBlockingQueueLockTest {
	@Test
	void multipleProducersConsumers() throws Exception {
		BoundedBlockingQueueLock<Integer> queue = new BoundedBlockingQueueLock<Integer>(5, false);

		int producers = 5;
		int consumers = 5; // sometimes consumers < producers fails the test
		int itemsPerProducer = 50;

		// Sub-interface of ExecutorService
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(6); // corePoolSize <= 5 fails the test
		AtomicInteger consumed = new AtomicInteger();

		// producers
		for (int p = 0; p < producers; p++) {
			scheduler.schedule(() -> {
				try {
					for (int i = 0; i < itemsPerProducer; i++) {
						queue.put(i);
					}
				} catch (InterruptedException ignored) {
				}
			}, 1, TimeUnit.SECONDS);
		}

		// consumers
		for (int c = 0; c < consumers; c++) {
			scheduler.schedule(() -> {
				try {
					for (int i = 0; i < itemsPerProducer; i++) {
						queue.take();
						consumed.incrementAndGet();
					}
				} catch (InterruptedException ignored) {
				}
			}, 1, TimeUnit.SECONDS);
		}

		scheduler.shutdown();// it no longer accepts any new tasks
		scheduler.awaitTermination(5, TimeUnit.SECONDS);
		assertEquals(producers * itemsPerProducer, consumed.get());
	}

}
