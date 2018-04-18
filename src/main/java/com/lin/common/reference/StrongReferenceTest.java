package com.lin.common.reference;

import java.io.IOException;

public class StrongReferenceTest {
	 B a;
	
	public static void main(String[] args) throws IOException {
		StrongReferenceTest test=new StrongReferenceTest();
		System.out.println("开始");
		test.create();
		if(test.a!=null){
			
		}
	}
	
	public void create(){
		 a=new B();
	}
	
	
	
	

}
  class B {
	
}
