/**
 * 
 */
package com.lin.common.oom;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author michaellin
 *
 */
public class ThreadCounter {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();
        while (true) {
            Thread thread = new Thread(() -> {
                counter.incrementAndGet();
                if (counter.get() % 100 == 0) {
                    System.out.printf("Number of threads created so far: %d%n", counter.get());
                }
                LockSupport.park();
            });
            thread.start();
        }
    }
}
