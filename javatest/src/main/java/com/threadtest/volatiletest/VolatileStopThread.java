package com.threadtest.volatiletest;

public class VolatileStopThread extends Thread {

//    private volatile boolean stop = false;
    private /*volatile*/ boolean stop = false;

    public void stopMe() {
        stop = true;
        System.out.println("stop = " + stop);
    }

    public void run() {
        int i = 0;
        while (!stop) {
            i++;
        }
        System.out.println("Stop thread");
    }

    public static void main(String args[]) throws InterruptedException {
        VolatileStopThread t = new VolatileStopThread();
        t.start();
        Thread.sleep(1000);
        t.stopMe();
        Thread.sleep(1000);
    }

}