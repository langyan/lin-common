package com.lin.common.concurrent;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class FutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 定义异步任务
        Callable<String> asyncTask = () -> {
            Thread.sleep(2000); // 模拟耗时操作
            return "Async task completed";
        };
        // 提交异步任务
        Future<String> future = executorService.submit(asyncTask);
        // 判断任务是否完成
        while (!future.isDone()) {
            System.out.println("Task not done yet...");
            Thread.sleep(500);
        }
        // 获取结果
        String result = future.get();
        System.out.println(result);
        // 关闭线程池
        executorService.shutdown();
    }
}