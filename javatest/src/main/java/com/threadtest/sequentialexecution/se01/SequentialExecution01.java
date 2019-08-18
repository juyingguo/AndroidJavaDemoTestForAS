package com.threadtest.sequentialexecution.se01;

import java.util.ArrayList;
import java.util.List;

public class SequentialExecution01 {
    public static void main(String[] args) {
        try {
            test1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static void test1() throws InterruptedException {
        int count = 10;
        List<Thread> workers = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            Thread worker = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行子线程" + Thread.currentThread());
                }
            });
            worker.start();
            workers.add(worker);
        }
        for(int i = 0; i < count; i++) {
            workers.get(i).join();
        }
        System.out.println("执行主线程");

    }
}
