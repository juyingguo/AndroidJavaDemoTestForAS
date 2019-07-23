package com.threadtest;

/**
 * Date:2019/7/19,11:24
 * author:jy
 */
public class MainThreadTest {
    public static void main(String[] args) {
        testThread();

    }

    private static void testThread() {

        System.out.println("testThread,Thread.currentThread():" + Thread.currentThread());
        try {
            Thread.sleep(1000);
            Thread.sleep(10*1000);
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("testThread,Thread.currentThread():" + Thread.currentThread());
    }

}
