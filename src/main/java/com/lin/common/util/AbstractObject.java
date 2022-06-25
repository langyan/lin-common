package com.lin.common.util;

public class AbstractObject {

	/**
	 * 关闭后
	 */
	public void afterClosed() {
		System.out.println("AbstractObject.afterClosed");
		
	}
	
	/**
	 * 垃圾回收，销毁
	 */
	public void onDestroy() {
		System.out.println("AbstractObject.onDestroy");
		this.afterClosed();
	}
}
