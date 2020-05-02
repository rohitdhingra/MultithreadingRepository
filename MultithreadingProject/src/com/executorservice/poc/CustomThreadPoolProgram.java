package com.executorservice.poc;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolProgram {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = new ThreadPoolExecutor(1, 10, 120, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50), new CustomRejectionHandler());

		try {
			for (int i = 0; i < 40000; i++) {
				service.execute(new Task(i));
			}
		} catch (RejectedExecutionException e) {
			System.err.println("task rejected" + e.getMessage());
		}
		List<Runnable> list= service.shutdownNow();
		System.out.println("Pending Tasks"+list.size());
		service.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println("Is Shutdown"+service.isShutdown());
		System.out.println("Is Terminated"+service.isTerminated());
		
		System.out.println("Thread Name: " + Thread.currentThread().getName());
	}

	private static class CustomRejectionHandler implements RejectedExecutionHandler{

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			// TODO Auto-generated method stub
			System.err.println("task rejected");
		}
		
	}
	
	private static class Task implements Runnable {

		int taskId;

		public Task(int taskId) {
			super();
			this.taskId = taskId;
		}

		@Override
		public void run() {
			System.out.println("TaskId " + taskId + " started by Thread Name: " + Thread.currentThread().getName());
			for (int i = 0; i < 1000000; i++) {

			}
			System.out.println("TaskId" + taskId + " end by Thread Name: " + Thread.currentThread().getName());

		}

	}
}
