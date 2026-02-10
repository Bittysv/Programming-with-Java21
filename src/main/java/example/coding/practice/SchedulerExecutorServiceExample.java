package example.coding.practice;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerExecutorServiceExample {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("A Count-down-clock");

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(11);
		System.out.println("Current Time (seconds): " + Calendar.getInstance().get(Calendar.SECOND));

		for (int i = 10; i >= 0; i--) {
			scheduler.schedule(new Task(i), i, TimeUnit.SECONDS);
		}

		System.out.println("**Rate Limiter**");
		RateLimiter rObj = new RateLimiter(5);
		boolean allowFlag;

		for (int i = 0; i < 5; i++) {
			allowFlag = rObj.allow("user1");
			System.out.println("Allow output: " + allowFlag);
//			scheduler.schedule(rObj.allow("user1"), i, TimeUnit.SECONDS);
		}
		System.out.println("Before sleep: " + rObj.allow("user1"));
		
		Thread.sleep(1000);
		System.out.println("After sleep: " +rObj.allow("user1"));
		scheduler.shutdown();
	}

}

class Task implements Runnable {
	private int num;

	public Task(int num) {
		this.num = num;
	}

	public void run() {
		System.out
				.println("Number: " + num + " Current Time (seconds): " + Calendar.getInstance().get(Calendar.SECOND));
	}
}

class TokenBucket {
	final int capacity;
	final double refillRatePerNanaos;
	double tokens;
	long lastRefillTime;

	public TokenBucket(int capacity) {
		this.capacity = capacity;
		this.tokens = capacity;
		this.refillRatePerNanaos = capacity / 1000000000.0;
		this.lastRefillTime = System.nanoTime();

	}

}

class RateLimiter {
	private final int permitsPerSecond;
	private final ConcurrentHashMap<String, TokenBucket> buckets = new ConcurrentHashMap<>();

	public RateLimiter(int permitsPerSecond) {
		this.permitsPerSecond = permitsPerSecond;
	}

	public boolean allow(String key) {
		TokenBucket bucket = buckets.computeIfAbsent(key, kay -> new TokenBucket(permitsPerSecond));

		synchronized (bucket) {
			refill(bucket);

			if (bucket.tokens >= 1) {
				bucket.tokens -= 1;
				return true;
			}
			return false;
		}
	}

	private void refill(TokenBucket bucket) {
		long now = System.nanoTime();
		long elapsed = now - bucket.lastRefillTime;

		double tokensToAdd = elapsed * bucket.refillRatePerNanaos;
		if (tokensToAdd > 0) {
			bucket.tokens = Math.min(bucket.capacity, bucket.tokens + tokensToAdd);
			bucket.lastRefillTime = now;
		}
	}
}
