package com.executorservice;


public class VolatileTest02 {
    static String TAG = VolatileTest02.class.getSimpleName();

    private VolatileTest02(){}

    static private VolatileTest02 instance ;
    public static VolatileTest02 getInstance(){
        if (instance == null){
            synchronized (VolatileTest02.class){
                if (instance == null){
                    instance = new VolatileTest02();
                }
            }
        }
        return instance;
    }

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


    private static void thread1() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("thread1()>>status:" + VolatileTest02.getInstance().status);
                   VolatileTest02.getInstance().changeStatus();
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
                    System.out.println("thread2()>>status:" + VolatileTest02.getInstance().status);
                        VolatileTest02.getInstance().run();
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
