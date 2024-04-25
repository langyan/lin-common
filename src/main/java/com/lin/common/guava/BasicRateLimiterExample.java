package com.lin.common.guava;

import java.util.Date;

import org.apache.tools.ant.util.DateUtils;

import com.google.common.util.concurrent.RateLimiter;

public class BasicRateLimiterExample {
    public static void main(String[] args) {
        // 创建一个每秒允许2个请求的RateLimiter
        RateLimiter limiter = RateLimiter.create(2.0);

        for (int i = 1; i <= 10; i++) {
            double waitTime = limiter.acquire(); // 请求令牌并获取等待时间
            System.out.println("时间:"+DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")+"处理请求 " + i + "，等待时间: " + waitTime + "秒");
        }
    }
}