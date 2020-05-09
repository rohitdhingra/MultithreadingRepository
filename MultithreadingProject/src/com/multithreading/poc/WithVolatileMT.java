package com.multithreading.poc;

public class WithVolatileMT {
	
	public static void main(String[] args) {

		ShareObject sharedObject = new ShareObject();
		Thread writerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				
				int sum = 0;
				for(int i=0;i<10;i++)
				{
				sum +=i;	
				}
				sharedObject.writerMethod(sum);
			}
			
		});
		Thread readerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				sharedObject.readerMethod();				
			}
			
		});
		writerThread.start();
		readerThread.start();
		
	}
	
	
}

class ShareObject
{
	int a=0,b=0,c=0;
	int i=0;
	
	public void writerMethod(int val)
	{
		a=1;
		b=1;
		c=1;
		synchronized(this)
		{
			i=val;
		}
		
	}
	public void readerMethod()
	{
		int r2 =0;
		synchronized(this)
		{
		r2 = i;
		}
		System.out.println("Reader Method"+r2+a+b+c);
	}
}