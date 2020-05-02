package com.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockingDemo {
	final Lock lock = new ReentrantLock();
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		new ReentrantLockingDemo().go();
	}

	private void go() {
		// TODO Auto-generated method stub
		new Thread(newRunnable(),"Thread1").start();
		new Thread(newRunnable(),"Thread2").start();
	}

	private Runnable newRunnable() {
		// TODO Auto-generated method stub
//		return null;
		return new Runnable()
		{

				public void run() {
				// TODO Auto-generated method stub
//				do
//				{	
					System.out.println("Inside thread"+Thread.currentThread().getName());
					try
					{
						lock.lockInterruptibly();
						Condition condition = lock.newCondition();
						
							try
							{
							System.out.println("locked thread"+Thread.currentThread().getName());
							Thread.sleep(10000);
							}
							finally
							{
								lock.unlock();
								System.out.println("unlocked thread "+Thread.currentThread().getName());
							}
//							break;
//						}
//						else
//						{
//							System.out.println("unable to lock thread"+Thread.currentThread().getName()+"will retry again" );
//						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//				}while(true);
		
			}
			
		};
//				return null;
	}
	
}
