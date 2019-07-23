package com.compute.operator;

import com.executorservice.Test01;

public class JavaOperatorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test01();
	}

	/**
	 * https://www.cnblogs.com/yulinfeng/p/6602902.html
	 */
	private static void Test01() {
		int leftShift = 10;
		/**
		 * 十进制:10, 二进制:1010
		 */
		System.out.println("十进制:" + leftShift + ", 二进制:" + Integer.toBinaryString(leftShift));
		int newLeftShift = leftShift << 2;
		/**
		 * 左移2位后十进制:40, 左移2位后二进制101000
		 */
		System.out.println("左移2位后十进制:" + newLeftShift + ", 左移2位后二进制" + Integer.toBinaryString(newLeftShift));    //正整数x左移n位后的十进制结果，x = x * 2^n
		
	}

}
