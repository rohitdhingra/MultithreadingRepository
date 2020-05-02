package com.concurrency;

public class InterruptedSleepingThread extends Thread{
	public void run()
	{
	doAPseudoHeavyWeightJob();
	}

	private void doAPseudoHeavyWeightJob() {
		// TODO Auto-generated method stub
		for(int i=0;i<Integer.MAX_VALUE;i++)
		{
				System.out.println(i +" "+i*2);
				if(Thread.currentThread().isInterrupted())
				{
					System.out.println("Thread Interrupted and Exiting");
					break;
				}
				else{
					System.out.println("Sleep Baby Sleep call");
					sleepBabySleep();
				}
		}
	}

	private void sleepBabySleep() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Inside Sleep Baby sleep");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(" SBS Interrupted Exception");
			Thread.currentThread().interrupt();
		}
		
	}
}
