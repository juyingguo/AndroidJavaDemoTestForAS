package com.threadtest.reentrantlock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithTryLockTest {
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new ThreadDemo(lock1, lock2),"t-one");//该线程先获取锁1,再获取锁2
        Thread thread1 = new Thread(new ThreadDemo(lock2, lock1),"t-two");//该线程先获取锁2,再获取锁1
        thread.start();
        thread1.start();
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
                while(!lock1.tryLock()){
                    System.out.println(Thread.currentThread().getName()+"-1-未获取到锁，休眠5ms-" + lock1.toString());
                    TimeUnit.MILLISECONDS.sleep(10);
                }
                System.out.println(Thread.currentThread().getName()+"获取了锁" + lock1.toString());
                while(!lock2.tryLock()){
                    System.out.println(Thread.currentThread().getName()+"-2-未获取到锁，休眠5ms-" + lock2.toString());
                    lock1.unlock();
                    TimeUnit.MILLISECONDS.sleep(10);
                }
                System.out.println(Thread.currentThread().getName()+"获取了锁" + lock2.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                firstLock.unlock();
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName()+"正常结束!");
            }
        }
    }
}