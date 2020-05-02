package com.concurrency;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CmdProcessBuilder {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		List<String> command = new ArrayList<String>();
		command.add(System.getenv("windir")+"\\system32\\"+"tree.com");
		command.add("/A");
		ProcessBuilder builder = new ProcessBuilder(command);
		Map<String,String> environ = builder.environment();
		builder.directory(new File(System.getenv("temp")));
		System.out.println("Directory :"+System.getenv("temp"));
		final Process process = builder.start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while((line = br.readLine())!=null)
		{
			System.out.println(line);
		}
		System.out.println("Program terminated");
		Thread.sleep(1000000);
	}

}
