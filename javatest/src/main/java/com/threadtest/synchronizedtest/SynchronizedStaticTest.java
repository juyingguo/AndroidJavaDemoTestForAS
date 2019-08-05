package com.threadtest.synchronizedtest;

/**
 * Date:2019/8/2,11:51
 * author:jy
 */
public class SynchronizedStaticTest {
    public static void main(String args[]){
        SyncThread01 syncThread1 = new SyncThread01();
        SyncThread01 syncThread2 = new SyncThread01();
        Thread thread1 = new Thread(syncThread1, "SyncThread1");
        Thread thread2 = new Thread(syncThread2, "SyncThread2");
        thread1.start();
        thread2.start();
    }

}

/**
 * 同步线程
 */
class SyncThread01 implements Runnable {
    private static int count;

    public SyncThread01() {
        count = 0;
    }

    /*public synchronized static void method() {
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
    public  void method() {
        /*synchronized (this.getClass()){
            for (int i = 0; i < 5; i ++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }*/
        synchronized (SyncThread01.class){
            for (int i = 0; i < 5; i ++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /*public synchronized void run() {
        method();
    }*/
    public /*synchronized*/ void run() {
        method();
    }
}