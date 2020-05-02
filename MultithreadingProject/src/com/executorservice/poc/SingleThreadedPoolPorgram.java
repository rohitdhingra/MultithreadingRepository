package com.executorservice.poc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadedPoolPorgram {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for(int i =0;i<100;i++)
		{
			service.execute(new Task(i));
		}
		System.out.println("Thread Name: "+Thread.currentThread().getName());
		service.shutdown();
		

	}
	private static class Task implements Runnable{
		int i;
		
		public Task(int i) {
			super();
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println("Task "+i+ " has been completed by"
					+ "Thread Name: "+Thread.currentThread().getName());
			
		}
		
	}

}
