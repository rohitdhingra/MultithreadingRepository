package com.executorservice.poc;

public class MultipleThreadsProgram {
	public static void main(String[] args) {
		for(int i = 0;i<1000000;i++)
		{
		Thread thread1 = new Thread(new Task());
		thread1.start();
		}
		System.out.println("Thread Name: "+Thread.currentThread().getName());
	}
	private static class Task implements Runnable{

		@Override
		public void run() {
			System.out.println("Thread Name: "+Thread.currentThread().getName());
		}
		
	}
}
