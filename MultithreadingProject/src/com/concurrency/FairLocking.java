package com.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLocking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("===========Explicit Test ==========");
		String [] myThreads = {"Thread One","Thread Two","Thread Three","Thread Four"};
		Lock lock = new ReentrantLock(true);
		for(int i=0;i<4;i++)
		{
		new Thread(new MyThread(lock),myThreads[i]).start();
		}
	}

}
