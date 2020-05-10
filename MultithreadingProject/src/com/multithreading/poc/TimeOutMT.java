package com.multithreading.poc;

public class TimeOutMT {

	public static void main(String[] args) {
		Thread t1 = new Thread(()-> {
			//task step1
			int i=0;
			while(!Thread.currentThread().isInterrupted())
			{
				
					System.out.println(i++);
				
			}
			//task step2
			
		});
		//1. Start the thread
		t1.start();
		long start = System.currentTimeMillis();
		
		//2. timeout for 10 seconds
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()-start);
		//3. stop the thread
		t1.interrupt();

	}

}
