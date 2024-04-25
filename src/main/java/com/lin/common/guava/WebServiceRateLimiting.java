package com.lin.common.guava;

import java.util.stream.IntStream;

import com.google.common.util.concurrent.RateLimiter;

public class WebServiceRateLimiting {
    private final RateLimiter rateLimiter = RateLimiter.create(5.0); // 每秒1个请求

    public void handleRequest(int requestId) {
        if (rateLimiter.tryAcquire(1)) {
            System.out.println("处理请求: " + requestId);
            // 进行实际的请求处理
        } else {
            System.out.println("请求 " + requestId + " 被限流");
            // 可以返回错误信息或者进行其他操作
        }
    }

    public static void main(String[] args) {
        WebServiceRateLimiting service = new WebServiceRateLimiting();
        IntStream.rangeClosed(1, 10).forEach(service::handleRequest);
    }
}