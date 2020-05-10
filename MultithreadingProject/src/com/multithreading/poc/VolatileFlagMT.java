package com.multithreading.poc;

public class VolatileFlagMT {
	public static void main(String[] args) throws InterruptedException {
		MyTask task = new MyTask();
		Thread t1 = new Thread(task);
		t1.start();
		Thread.sleep(2000);
		task.keepRunning =false;
	}
}
class MyTask implements Runnable
{
	public volatile boolean keepRunning =true;

	
	@Override
	public void run() {
		int i=0;
		while(keepRunning==true)
		{
			System.out.println(i++);
		}
		
	}
	
}
