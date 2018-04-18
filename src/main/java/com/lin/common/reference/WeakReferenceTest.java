package com.lin.common.reference;

import java.lang.ref.WeakReference;

/**
 * 
 * 在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存
 * 
 * @author michael
 *
 */
public class WeakReferenceTest {
	
	 public static void main(String[] args) {
	     
	        WeakReference<String> sr = new WeakReference<String>(new String("hello"));
	         
	        System.out.println(sr.get());
	        System.gc();                //通知JVM的gc进行垃圾回收
	        System.out.println(sr.get());
	    }

}
