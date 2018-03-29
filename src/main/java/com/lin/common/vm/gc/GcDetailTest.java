package com.lin.common.vm.gc;

import java.util.LinkedList;
import java.util.Queue;

/*
 * -XX:+PrintGCDetails -Xloggc:D:/log/log
 * 
 */
public class GcDetailTest {

	 public static void main(String[] args) {  
	        Queue<int[]> queue = new LinkedList<int[]>();  
	        while (true) {  
	            int[] tmp=new BigData().execute();  
	            if (queue.size() > 100000) {  
	                queue.poll();  
	            } else {  
	                queue.offer(tmp);  
	            }  
	        }  
	  
	    }  
	 
}
