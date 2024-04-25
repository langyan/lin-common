package com.lin.common.guava;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterDemo {
    public static void main(String[] args) {
        // 创建一个每秒放入5个令牌的RateLimiter
//        RateLimiter limiter = RateLimiter.create(20.0);
//        System.out.println(limiter.tryAcquire(2));
//        System.out.println(limiter.tryAcquire(2));
//        System.out.println(limiter.tryAcquire(2));
//        
        // 创建一个每秒放入5个令牌的 RateLimiter，并设置预热期为1秒
        RateLimiter limiter = RateLimiter.create(5.0);
//        try {
//            Thread.sleep(1000L);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        // 模拟请求处理
//        for (int i = 0; i < 10; i++) {
//            // 请求一个令牌
//            System.out.println(limiter.tryAcquire());
//            // 模拟处理请求
//            System.out.println("处理请求 " + i);
//        }
        compareBurstyRequest() ;
    }
    
    public static void compareBurstyRequest() {
        RateLimiter burstyLimiter = RateLimiter.create(5);
        RateLimiter warmingUpLimiter = RateLimiter.create(5, 2, TimeUnit.SECONDS);

        DecimalFormat df = new DecimalFormat("0.00");
        // 积攒1秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("稳定限流器");
        IntStream.range(0, 10).forEach(a -> {
            System.out.println("处理请求 " + a + " " + burstyLimiter.tryAcquire(2));
//            double acquire = burstyLimiter.acquire();
//            System.out.println("第" + a + "次请求等待时间：" + df.format(acquire));
        });

        System.out.println("预热限流器");
        IntStream.range(0, 10).forEach(a -> {
            System.out.println("处理请求 " + a + " " + warmingUpLimiter.tryAcquire());
//            double acquire = warmingUpLimiter.acquire();
//            System.out.println("第" + a + "次请求等待时间：" + df.format(acquire));
        });
    }

}