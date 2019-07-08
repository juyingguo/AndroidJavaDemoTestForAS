package com.example;

import java.util.Random;

/**
 * Created by Administrator on 2018/7/13.
 */

public class RandomTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    int index = new Random().nextInt(2);
                    System.out.println("index:" + index);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }
}
