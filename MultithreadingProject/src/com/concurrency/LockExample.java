package com.concurrency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
	private static final String fileName ="LockExample.txt";
	private static final String EXIT_FLAG ="BYE";
	private static final int NO_OF_LINES =10;
	private static final Lock fileLock = new ReentrantLock();
	private static final Condition condition = fileLock.newCondition();
	private static final ExecutorService executorPool = Executors.newFixedThreadPool(2);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable fileWriter = new FileWrite();
		Runnable fileReader = new FileRead();
		executorPool.submit(fileReader);
		executorPool.submit(fileWriter);
		executorPool.shutdown();

	}
	public static class FileWrite implements Runnable {

		public void run() {
			// TODO Auto-generated method stub
			fileLock.lock();
			for(int i=0;i< NO_OF_LINES;i++)
			{
				try {
					PrintWriter writer = new PrintWriter(new File(fileName));
					if(i != NO_OF_LINES -1)
					{
						int random = new SecureRandom().nextInt();
						System.out.println("WRITER writing "+random);
						writer.println(random);
						writer.close();
						condition.signal();
						System.out.println("Writer waiting");
						condition.await();
					}
					else
					{
						writer.println(EXIT_FLAG);
						System.out.println("WRITER WRITING");
						writer.close();
						condition.signal();
					}
						
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("!!!!!!!!!!!!!!!!!EXECPTIOON!!!!!!!!");
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					fileLock.unlock();
					File file = new File(fileName);
					file.delete();
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		}

	}
	public static class FileRead implements Runnable {

		public void run() {
			// TODO Auto-generated method stub
			String data = null;
			fileLock.lock();
			while(true)
			{
				try {
					BufferedReader reader = new BufferedReader(new FileReader(fileName));
					data = reader.readLine();
					System.out.println("READ DATA "+data);
					reader.close();
					if(data==null ||!data.equals(EXIT_FLAG))
					{
						condition.signalAll();
						System.out.println("Reader Waiting");
						
						condition.await();
					}
					else
					{
						System.out.println("READER EXITING");
						condition.signal();
						break;
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

	}
}

