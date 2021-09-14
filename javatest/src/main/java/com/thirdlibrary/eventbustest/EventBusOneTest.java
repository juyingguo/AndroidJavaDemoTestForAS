package com.thirdlibrary.eventbustest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Date:2021/9/13,9:26
 * author:jy
 */
public class EventBusOneTest {
    public static void main(String[] args) {

        printThread();

        First first = new First();
        first.register();
        Second second = new Second();
        second.register();

//        first.post();
        post();

//        first.unRegister();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void post(){
        MessageEvent messageEvent = new MessageEvent("hello eventbus test in java.");
        EventBus.getDefault().post(messageEvent);
    }

    private static void printThread(){
        System.out.println("Thread.currentThread().getName():" + Thread.currentThread().getName());
    }
}
