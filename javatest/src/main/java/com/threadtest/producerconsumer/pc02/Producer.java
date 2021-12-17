package com.threadtest.producerconsumer.pc02;
 
import java.util.Stack;
 
public class Producer extends Thread{
	
	private String TAG = Producer.class.getSimpleName();
	private final Stack<Integer> stack;
	
	Producer( Stack<Integer> stack){
		super();
		this.stack = stack;
	}
	
	@Override
	public void run() {
		int i=0;
		synchronized (stack) {
			while(i<20){
				if(stack.empty()){//为空,生产数据.
					stack.push(i);
					System.out.println(TAG + ",push"+i);
					i++;
					stack.notify();
				}else{//不为空,等待数据被消费					
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
