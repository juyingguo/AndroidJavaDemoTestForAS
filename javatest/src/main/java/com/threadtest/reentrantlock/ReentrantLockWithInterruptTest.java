package com.threadtest.reentrantlock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithInterruptTest {
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadDemo(lock1, lock2),"t-one");//该线程先获取锁1,再获取锁2
        Thread thread1 = new Thread(new ThreadDemo(lock2, lock1),"t-two");//该线程先获取锁2,再获取锁1
        thread.start();
        thread1.start();

        TimeUnit.MILLISECONDS.sleep(10);//更好的触发死锁
        thread.interrupt();//是第一个线程中断
    }

    static class ThreadDemo implements Runnable {
        Lock firstLock;
        Lock secondLock;
        public ThreadDemo(Lock firstLock, Lock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }
        @Override
        public void run() {
            try {
                firstLock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName()+"获取了锁" + firstLock.toString());
                TimeUnit.MILLISECONDS.sleep(5);//更好的触发死锁
                secondLock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName()+"获取了锁" + secondLock.toString());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                    firstLock.unlock();
                    secondLock.unlock();
                try {
                }catch (Exception e){
                }
                System.out.println(Thread.currentThread().getName()+"正常结束!");
            }
        }
    }
}