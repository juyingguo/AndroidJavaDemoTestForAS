package com.executorservice;


public class TestVolatile {
    static String TAG = TestVolatile.class.getSimpleName();
    boolean status = false;

    /**
     * 状态切换为true
     */
    public void changeStatus(){
        status = true;
    }

    /**
     * 若状态为true，则running。
     */
    public void run(){
        if(status){
            System.out.println("run()>>running....>>>status:" + status);
        }
    }
    public static void main(String[] args) {
        thread1();
        thread2();
    }

    static TestVolatile testVolatile1 = new TestVolatile();
    static TestVolatile testVolatile2 = new TestVolatile();

    private static void thread1() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    testVolatile1.changeStatus();
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();

    }

    private static void thread2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                        testVolatile2.run();
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
