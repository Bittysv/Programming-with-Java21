package example.coding.practice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureEx {
	
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public Future<Integer> calculate(Integer input){
		return executor.submit(() -> {
			Thread.sleep(1000);
			return input*input;
		});
	}
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureEx obj = new FutureEx();
		var result = obj.calculate(10).get();
		System.out.println("Result = "+result);
	}

}
