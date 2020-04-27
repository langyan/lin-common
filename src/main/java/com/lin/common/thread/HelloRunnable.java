/**
 * 
 */
package com.lin.common.thread;

/**
 * @author User
 * 
 */
public class HelloRunnable implements Runnable {

	public void run() {
		System.out.println("Hello from a thread!");
	}

	public static void main(String args[]) {
		(new Thread(new HelloRunnable())).start();

		String a=null;
	}

}
