package com.threadtest.sequentialexecution.abcse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * java实现三个线程A B C，同时开始执行，C处于阻塞状态，当A,B运行结束后，C开始执行。
 * 其中A操作内容为对num加2循环两次，B操作内容为对num加2循环两次,C操作内容为对num加2循环两次
 */
public class CountDownLatchABCTestAtomicComputerNum {
    public static void main(String[] args) {


        test1();


    }
    final static CountDownLatch latch = new CountDownLatch(2);
    static AtomicInteger atomicIntegerNum = new AtomicInteger(0);
    private static void test1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                try {
                    for(int i=0;i<100;i++){
                        atomicIntegerNum.addAndGet(2);
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                System.out.println("子线程"+Thread.currentThread().getName()+"统计数字：："+atomicIntegerNum.get());
                latch.countDown();
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                try {
                    for(int i=0;i<100;i++){
                        atomicIntegerNum.addAndGet(2);
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                System.out.println("子线程"+Thread.currentThread().getName()+"统计数字：："+atomicIntegerNum.get());
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
                    for(int i=0;i<100;i++){
                        atomicIntegerNum.addAndGet(2);
                        Thread.sleep(10);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                System.out.println("子线程"+Thread.currentThread().getName()+"统计总数：："+atomicIntegerNum.get());
                System.out.println();
            }
        },"C").start();
    }
}
