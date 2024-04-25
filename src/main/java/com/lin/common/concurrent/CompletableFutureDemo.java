package com.lin.common.concurrent;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        // 定义异步任务
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2); // 模拟耗时操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Async task completed";
        });
        // 判断任务是否完成
        while (!future.isDone()) {
            System.out.println("Task not done yet...");
            TimeUnit.MILLISECONDS.sleep(500);
        }
        // 获取结果
        String result = future.get();
        System.out.println(result);
    }
}