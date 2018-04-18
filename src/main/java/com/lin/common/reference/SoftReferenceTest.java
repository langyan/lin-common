package com.lin.common.reference;

import java.lang.ref.SoftReference;

/**
 * 只有在内存不足的时候JVM才会回收该对象
 * 很适合用来实现缓存
 * @author michael
 *
 */
public class SoftReferenceTest {

	public static void main(String[] args) {
        
        SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        System.out.println(sr.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println(sr.get());
    }

	private static void use() {
		System.out.println("开始");

		A a = new A();

		SoftReference<A> sr = new SoftReference<A>(a);
		a = null;
		if (sr != null) {
			a = sr.get();
		} else {
			a = new A();
			sr = new SoftReference<A>(a);
		}

		System.out.println("结束");
	}

}

class A {
	int[] a;

	public A() {
		a = new int[100000000];
	}
}
