package com.lin.common.thread;


import java.util.Timer;

public class DaemonThread {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("start");
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("End Main");

    }

}
