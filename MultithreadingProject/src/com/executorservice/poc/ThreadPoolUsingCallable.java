package com.executorservice.poc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadPoolUsingCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		ExecutorService service = Executors.newFixedThreadPool(10);
		List<Future> allFutures = new ArrayList<>();
		for(int i = 0;i<100;i++)
		{
			Future<Integer> future = service.submit(new Task());
			allFutures.add(future);
		}
		for(int i=0;i<100;i++)
		{
			Future<Integer> future = allFutures.get(i);
			if(i==10)
			{
				future.cancel(true);
				System.out.println("Is cancelled"+future.isCancelled());
				System.out.println("Is Done"+future.isDone());
			}
			System.out.println("Is cancelled"+future.isCancelled());
			Integer result = future.get();//blocking until get response
//			Integer result = future.get(1,TimeUnit.SECONDS);//timeout exception will be thrown
			System.out.println("Future Result for"+i+":"+result);
		}
			
	}
	private static class Task implements Callable<Integer>
	{

		@Override
		public Integer call() throws Exception {
			Thread.sleep(3000);
			return new Random().nextInt();
		}
		
	}

}
