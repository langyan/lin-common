package com.lin.common.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。
 * 它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 * CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，
 * 每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。
 * @author michael
 *
 */
public class CyclicBarrierTest {

	static CyclicBarrier c = new CyclicBarrier(2,new A());

	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					c.await();
				} catch (Exception e) {
				}
				System.out.println(1);
			}

		}).start();
		try {
			c.await();
		} catch (Exception e) {
		}
		System.out.println(2);

	}
	 static class A implements Runnable {

		@Override
		public void run() {
			 System.out.println(3);
		}
	 }

}
