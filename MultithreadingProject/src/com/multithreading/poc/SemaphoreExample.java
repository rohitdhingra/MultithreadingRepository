package com.multithreading.poc;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(2);
		new PrintThread(1,semaphore).start();
		new PrintThread(2,semaphore).start();
		new PrintThread(3,semaphore).start();
		new PrintThread(4,semaphore).start();
	}
}
