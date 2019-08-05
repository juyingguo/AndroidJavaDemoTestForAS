package com.threadtest.producerconsumer.pc02;

import java.util.Stack;

public class Consumer2 extends Thread{
	private String TAG = Consumer2.class.getSimpleName();
	private Stack<Integer> stack;

	Consumer2(Stack<Integer> stack){
		super();
		this.stack = stack;
	}
	
	@Override
	public void run() {
		synchronized (stack) {
			while(true){
				if(!stack.empty()){
					int pop = stack.pop();
					System.out.println(TAG + ",pop"+pop);
					stack.notify();
				}else{//等待				
					try {
						stack.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}		
	}
}
