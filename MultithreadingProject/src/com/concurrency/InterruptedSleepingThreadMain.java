package com.concurrency;

public class InterruptedSleepingThreadMain {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		InterruptedSleepingThread thread = new InterruptedSleepingThread();
		thread.start();
		Thread.sleep(10000);
		thread.interrupt();
	}

}
