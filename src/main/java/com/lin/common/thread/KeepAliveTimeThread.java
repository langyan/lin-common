package com.lin.common.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class KeepAliveTimeThread {

	public static void main(String[] args) throws InterruptedException {
        // 创建了一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                2,
                0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1));

        for (int i = 0; i < 3; i++) {
            executor.execute(new DemoTask(i));
        }

        while (true) {
            System.out.println("总线程数：" + executor.getPoolSize() + ", 当前活跃线程数：" + executor.getActiveCount());
            TimeUnit.SECONDS.sleep(1);
        }
    }

    static class DemoTask implements Runnable {
        int num;

        public DemoTask(int i) {
            this.num = i;
        }

        @Override
        public void run() {
            System.out.println("num=" + num + " Thread = " + Thread.currentThread().getName());
            if (num >= 1) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("num=" + num + " sleep 1 s结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("num=" + num + " sleep 3 s结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
