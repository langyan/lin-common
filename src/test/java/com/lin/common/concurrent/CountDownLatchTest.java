/**
 * 
 */
package com.lin.common.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

/**
 * @author michaellin
 *
 */
public class CountDownLatchTest {

    @Test
    void testAwait() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        try {
            while (countDownLatch.await(2, TimeUnit.SECONDS)) {
                System.out.println("await");
                break;
            }
            System.out.println("end");
        } catch (Exception e) {
            System.out.println("执行告警失败");
        }
        System.out.println("done");

    }

    @Test
    void testAwait1() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.countDown();
        try {
            while (countDownLatch.await(2, TimeUnit.SECONDS)) {
                System.out.println("await");
                break;
            }
            System.out.println("end");
        } catch (Exception e) {
            System.out.println("执行告警失败");
        }
        System.out.println("done");

    }

}
