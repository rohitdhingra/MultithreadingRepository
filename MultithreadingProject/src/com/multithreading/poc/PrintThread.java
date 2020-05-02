package com.multithreading.poc;

import java.util.concurrent.Semaphore;

public class PrintThread extends Thread{

	int id;
	Semaphore semaphore;
	public PrintThread(int id,Semaphore semaphore) {
		this.id = id;
		this.semaphore = semaphore;
	}

	public void run() {
		try {
			semaphore.acquire();
		//critical section
		System.out.println("Printer "+ id+" is printing");
		Thread.sleep(2000);
		semaphore.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
