package com.threadtest.producerconsumer.pc02;
 
import java.util.Stack;

/**
 * 	Java-经典消费者和生产者代码(面试题)
 https://blog.csdn.net/qq_33300026/article/details/83828507
 */
public class DemoClass {
	public static void main(String[] args) throws InterruptedException {
		Stack<Integer> stack = new Stack<Integer>();
		Producer p = new Producer(stack);
		Consumer c = new Consumer(stack);
//		Consumer2 c2 = new Consumer2(stack);
		p.start();
		c.start();
//		c2.start();
	}
}
