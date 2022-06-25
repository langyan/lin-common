/**
 * 
 */
package com.lin.common.binary;


import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

/**
 * @author michaellin
 *
 */
public class IntegerBinaryTest {
	
	@Test
	public void leftShift1 () {
		int leftShift = 1;
		System.out.println("十进制:" + leftShift + ", 二进制:" + Integer.toBinaryString(leftShift));
		int newLeftShift = leftShift << 6;
		//正整数x左移n位后的十进制结果，x = x * 2^n
		System.out.println("左移2位后十进制:" + newLeftShift + ", 左移2位后二进制" + Integer.toBinaryString(newLeftShift));   
	}
	
	@Test
	public void leftShift () {
		int leftShift = 10;
		System.out.println("十进制:" + leftShift + ", 二进制:" + Integer.toBinaryString(leftShift));
		int newLeftShift = leftShift << 2;
		//正整数x左移n位后的十进制结果，x = x * 2^n
		System.out.println("左移2位后十进制:" + newLeftShift + ", 左移2位后二进制" + Integer.toBinaryString(newLeftShift));   
	}
	
	@Test
	public void rightShift () {
		int rightShift = 10;
		System.out.println("十进制:" + rightShift + ", 二进制:" + Integer.toBinaryString(rightShift));
		int newRightShift = rightShift >> 2;
		//右移n位后的运算数x十进制结果，x = x / 2
		System.out.println("右移2位后十进制:" + newRightShift + ", 右移2位后二进制" + Integer.toBinaryString(newRightShift));    
	}

}
