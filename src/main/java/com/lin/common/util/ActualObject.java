/**
 * 
 */
package com.lin.common.util;

/**
 * @author michaellin
 *
 */
public class ActualObject extends AbstractObject {
	
	/**
	 * 关闭后
	 */
	public void afterClosed() {
		System.out.println("ActualObject.afterClosed");
	}
	
	public static void main(String[] args) {
		ActualObject object=new ActualObject();
		object.onDestroy();
	}

}
