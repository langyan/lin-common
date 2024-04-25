package com.lin.common.thread;

public class DaemonThread {

    public static void main(String[] args) {

        boolean test = true;
        Thread thread = new Thread(() -> {
            System.out.println("start");
            long i = 0;
            try {
                while (test) {
                    Thread.sleep(10);
                    i++;
                }
                System.out.println("sleep");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("stop");
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("End Main");

    }

}
