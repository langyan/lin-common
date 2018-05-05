package com.lin.common.reflect;

public class ClassNameTest {
	
	   public static void main(String[] args)  {  
		   byte[] a="a".getBytes();
		   System.out.println(a.getClass().getName());
		   
		   
		   char[] b="a".toCharArray();
		   System.out.println(b.getClass().getName());
	   }

}
