package com.multithreading.poc;

import java.util.concurrent.atomic.AtomicInteger;

public class UseOfAtomicInteger {

	public static void main(String[] args) {
		SharedObject obj = new SharedObject();
		Thread t1 = new Thread(new Task(obj));
		Thread t2 = new Thread(new Task(obj));
		t1.start();
		t2.start();
	}

	private static class Task implements Runnable {

		private SharedObject obj;
		
		public Task()
		{
			obj =null;
		}
		public Task(SharedObject obj)
		{
			this.obj = obj;
		}
		@Override
		public void run() {
			
			obj.increment();
			obj.getValue();
			
		}

	}
}

class SharedObject {
//	int i = 0;
AtomicInteger i = new AtomicInteger(0);
	public void increment() {
		i.incrementAndGet();
	}
	public void getValue()
	{
		System.out.println("Thread Name:"+Thread.currentThread().getName()+" value is : "+i.get());
	}

}
