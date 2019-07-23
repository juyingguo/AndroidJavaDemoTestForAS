package com.threadtest.volatiletest;

public class TestMain {

//    static volatile boolean flag = true;
    static /*volatile*/ boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " running..");
                }
                System.out.println(Thread.currentThread().getName() + "线程停止，死循环被打开");
            }
        }).start();
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                flag = false;
                System.out.println(Thread.currentThread().getName() + "修改flag为" + flag);
            }
        }).start();

        Thread.sleep(Integer.MAX_VALUE);
    }
}