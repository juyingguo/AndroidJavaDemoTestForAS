package com.executorservice;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test01 {
    private final static  String TAG = Test01.class.getSimpleName();
    public static void main(String[] args) {
        test1();


    }

    static ExecutorService executor = Executors.newSingleThreadExecutor();
    private static void test1() {
        for (int i= 0 ; i <2;i++){
            executor.execute(new TestRunnable());
        }

        try {
            Thread.sleep(1000);


            executor.shutdown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            // Wait a while for existing tasks to terminate
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executor.awaitTermination(8, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }

    }
    static class TestRunnable implements Runnable{

        @Override
        public void run() {
            for (int i= 0 ; i <2;i++){

                System.out.println(TAG + ">>thread:" + Thread.currentThread().getName() + ",i=" + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
