package com.threadtest.sequentialexecution.abcse02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread3 implements Runnable {

	final Lock lock = new ReentrantLock();

	private String name;

	public Thread3(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 1; i < 10; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("线程" + name + ":" + i);
		}
	}

	public static void main(String[] args) {
		Thread3 t1 = new Thread3("A");
		Thread3 t2 = new Thread3("B");
		Thread3 t3 = new Thread3("C");

		Thread A = new Thread(t1);
		Thread B = new Thread(t2);
		Thread C = new Thread(t3);

		try {
			B.start();
			B.join();
			C.start();
			C.join();
			A.start();
			A.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
