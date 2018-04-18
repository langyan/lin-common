package com.lin.common.vm;

/**
 * volatile变量自增运算测试
 * 加了volatile和没加volatile都无法解决非原子操作的线程同步问题
 */
public class VolatileTest {

    public static final int THREADS_COUNT = 20;
    public  volatile int race = 0;

    public  void increase() {
        race=race+1;
    }
    public  void increase2() {
        race++;
    }
    public int getRace(){
    	return race;
    }

    public static void main(String[] args) {
    	VolatileTest test=new VolatileTest();
        Thread[] thread = new Thread[THREADS_COUNT];

        for (int i = 0; i < THREADS_COUNT; i++) {
            thread[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                    	test.increase();
                    }
                }
            });
            thread[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(test.getRace());
    }

}
