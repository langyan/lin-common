package com.lin.common.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic变量自增测试
 * 线程安全
 */
public class AtomicTest {

    private static final int THREADS_COUNT = 20;
    public static AtomicInteger race = new AtomicInteger(0);

    public static void increase() {
        race.incrementAndGet();
    }

    public static void main(String[] args) {

        Thread[] thread = new Thread[THREADS_COUNT];

        for (int i = 0; i < THREADS_COUNT; i++) {
            thread[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            });
            thread[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(race);
    }
}
