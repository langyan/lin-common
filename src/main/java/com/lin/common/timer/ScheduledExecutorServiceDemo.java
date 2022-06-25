package com.lin.common.timer;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/*
 * 基于 ScheduledExecutorService 方式,相对的比 Timer 要好
 */
public class ScheduledExecutorServiceDemo {
	
	 public static void main(String[] args) throws InterruptedException {
		 
		 
		 System.out.println("执行任务A:" + LocalDateTime.now());
		 CountDownLatch countDownLatch = new CountDownLatch(10);
	        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
	        // 参数：1、具体执行的任务   2、首次执行的延时时间
	        //      3、任务执行间隔     4、间隔时间单位
	        service.scheduleAtFixedRate(() -> {System.out.println("执行任务A:" + LocalDateTime.now());
	        countDownLatch.countDown();
	        }, 2, 3, TimeUnit.SECONDS);
	        countDownLatch.await();
	    }

}
