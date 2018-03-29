package com.lin.common.vm.gc;

import java.util.HashMap;

/**
 * 
 * -Xmx512M -Xms512M -XX:+UseParNewGC -XX:+PrintGCDetails
 * 
 * @author michael
 *
 */
public class GCTimeTest {
	static HashMap map = new HashMap();

	public static void main(String[] args) {
		long begintime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			if (map.size() * 512 / 1024 / 1024 >= 400) {
				map.clear();// 保护内存不溢出
				System.out.println("clean map");
			}
			byte[] b1;
			for (int j = 0; j < 100; j++) {
				b1 = new byte[512];
				map.put(System.nanoTime(), b1);// 不断消耗内存
			}
		}
		long endtime = System.currentTimeMillis();
		System.out.println(endtime - begintime);
	}
}