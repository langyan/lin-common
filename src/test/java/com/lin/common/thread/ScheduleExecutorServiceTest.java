package com.lin.common.thread;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ScheduleExecutorServiceTest {

    CountDownLatch countDownLatch = new CountDownLatch(10);

    @Test
    public void test1() throws InterruptedException {
        ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor();
        scheduled.scheduleWithFixedDelay(() -> {

            System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            countDownLatch.countDown();
        }, 0, 1, TimeUnit.SECONDS);
        System.out.println("---- end ----");

        countDownLatch.await();
        scheduled.shutdown();
    }

}
