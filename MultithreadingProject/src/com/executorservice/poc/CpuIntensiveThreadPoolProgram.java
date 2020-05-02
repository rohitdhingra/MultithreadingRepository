package com.executorservice.poc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CpuIntensiveThreadPoolProgram {
	public static void main(String[] args) {
		
		//get count of available cores
		
		int coreCount =  Runtime.getRuntime().availableProcessors();
		System.out.println("Available Core Count is:"+coreCount);
		// create the pool
		ExecutorService service = Executors.newFixedThreadPool(coreCount);

		// submit the tasks for execution
		for (int i = 0; i < 100; i++) {
			service.execute(new Task());
		}
		System.out.println("Thread Name: " + Thread.currentThread().getName());
	}

	private static class Task implements Runnable {

		@Override
		public void run() {
			//Core CPU Intensive Like Crypting the password,algorithms,etc
			System.out.println("Thread Name: " + Thread.currentThread().getName());
		}

	}
}
