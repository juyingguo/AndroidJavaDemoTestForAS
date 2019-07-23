package com.threadtest;

public class ThreadInterruptTest01 {
    private final static  String TAG = ThreadInterruptTest01.class.getSimpleName();
    public static void main(String[] args) {
        test1();
        testThread();

    }

    private static void testThread() {

        System.out.println("testThread,Thread.currentThread():" + Thread.currentThread());
    }

    private static void test1() {

        oneThread.start();
        twoThread.start();

        try {
            Thread.sleep(3000);
            oneThread.interrupt();

            twoThread.isRun = false;

            Thread.sleep(2000);
            System.out.println("twoThread.isAlive()>>>>:" +  twoThread.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static OneThread oneThread = new OneThread();
    static class  OneThread extends Thread{
        @Override
        public void run() {
            super.run();
            int i = 0;
            try {
                while (true) {
//                    if (this.interrupted()) {
//                        throw new InterruptedException();
//                    }
                    Thread.sleep(1000);
                    System.out.println("OneThread>>>>running" );
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    static  TwoThread twoThread = new TwoThread();
    static class  TwoThread extends Thread{
        boolean isRun = true;
        @Override
        public void run() {
            super.run();
            try {
                while (isRun) {
                    Thread.sleep(1000);
                    System.out.println("TwoThread>>>running");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
