package com.concurrency;

import java.util.concurrent.locks.Lock;

public class MyThread implements Runnable {
	private Lock lock;
	public MyThread(Lock lock)
	{
		super();
		this.lock = lock;
	}

	public void run() {
		// TODO Auto-generated method stub
		printMessage("Entered run method...trying to lock monitor object");
		lock.lock();
		try
		{
		printMessage("Locked monitor object");
		try {
			Thread.currentThread().getThreadGroup().resume();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}finally
		{
			printMessage("Releasing Lock");
			lock.unlock();
		}
		printMessage("End of run method");
	}
	private void printMessage(String string) {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+string);
	}

}
