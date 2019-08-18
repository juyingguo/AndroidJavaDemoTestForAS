package com.threadtest.sequentialexecution.abcse;

import java.util.concurrent.CountDownLatch;

/**
 * java实现三个线程A B C，同时开始执行，C处于阻塞状态，当A,B运行结束后，C开始执行。
 */
public class CountDownLatchABCTest {
    public static void main(String[] args) {


        test1();


    }
    final static CountDownLatch latch = new CountDownLatch(2);
    private static void test1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                latch.countDown();
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                latch.countDown();
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
            }
        },"C").start();
    }
}
