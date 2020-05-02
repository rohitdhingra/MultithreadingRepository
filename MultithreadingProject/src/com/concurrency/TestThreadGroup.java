package com.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadGroup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lock lock = new ReentrantLock(true);
		ThreadGroup tgp = new ThreadGroup("rohitTGP");
		Thread t1 = new Thread(tgp,new MyThread(lock),"Thread1");
		Thread t2[] = {t1};
		System.out.println("Thread Group Active Group Count:"+tgp.activeGroupCount());
		t1.start();
		System.out.println("Thread Group Name :" +tgp.getName());
		System.out.println("Thread Group Parent:"+tgp.getParent());
//		tgp.suspend();
		System.out.println("Thread Group Priority:"+tgp.getMaxPriority());
		System.out.println("Thread Group Daemon:"+tgp.isDaemon());
		System.out.println("Thread Group Destroyed:"+tgp.isDestroyed());
		System.out.println("Thread Group Active Group Count:"+tgp.activeGroupCount());
		System.out.println("Thread Group Enumerate:"+tgp.enumerate(t2));
		
		System.out.println("Thread Group Allow Thread Suspension" +tgp.allowThreadSuspension(true));
		System.out.println("Thread Group Active Group Count:"+tgp.toString());
		tgp.list();
		
	}

}
