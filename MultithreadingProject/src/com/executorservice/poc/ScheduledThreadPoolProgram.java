package com.executorservice.poc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledThreadPoolProgram {

	public static void main(String[] args) {
		//for scheduling of tasks
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		
		//Task to run after 10 seconds delay
		service.schedule(new Task("normal"), 10, TimeUnit.SECONDS);
		
		//task to run repeatedly every 10 seconds
		service.scheduleAtFixedRate(new Task("Fixed Rate"), 15, 10, TimeUnit.SECONDS);
		
		//task to run repeatedly 10 seconds after previous task complets
		service.scheduleWithFixedDelay(new Task("Fixed Delay"), 15, 10, TimeUnit.SECONDS);
		
		System.out.println("Thread Name: "+Thread.currentThread().getName());
	}
	static class Task implements Runnable{
		private String name;
		

		public Task(String name) {
			super();
			this.name = name;
		}


		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Task Name "+name +" has been executed by"
					+ "Thread Name: "+Thread.currentThread().getName());
		}
		
	}

}
