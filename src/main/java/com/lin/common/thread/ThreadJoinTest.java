package com.lin.common.thread;

/**
 * 线程实例的方法join()方法可以使得一个线程在另一个线程结束后再执行。
 * 如果join()方法在一个线程实例上调用，当前运行着的线程将阻塞直到这个线程实例完成了执行。
 * 
 * 在join()方法内设定超时，使得join()方法的影响在特定超时后无效。当超时时，主方法和任务线程申请运行的时候是平等的。然而，当涉及sleep时，join()方法依靠操作系统计时，所以你不应该假定join()方法将会等待你指定的时间。
 * 
 * 像sleep,join通过抛出InterruptedException对中断做出回应。
 * 
 * @author michael
 *
 */
public class ThreadJoinTest {
	
	 public static void main(String[] args) throws InterruptedException
	   {
	      Thread t = new Thread(new Runnable()
	         {
	            public void run()
	            {
	               System.out.println("First task started");
	               System.out.println("Sleeping for 2 seconds");
	               try
	               {
	                  Thread.sleep(2000);
	               } catch (InterruptedException e)
	               {
	                  e.printStackTrace();
	               }
	               System.out.println("First task completed");
	            }
	         });
	      Thread t1 = new Thread(new Runnable()
	         {
	            public void run()
	            {
	               System.out.println("Second task completed");
	            }
	         });
	      t.start(); // Line 15
	      t.join(); // Line 16
	      t1.start();
	   }

}
