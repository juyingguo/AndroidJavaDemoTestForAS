package com.example;

import java.util.Date;

/**
 * Created by Administrator on 2018/6/21.
 */

public class DateTest {
    public static void main(String[] args) {
        for (int i=0 ;i<Integer.MAX_VALUE ;i++){
            try {
                Thread.sleep(1000*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long expiredTime = new Date().getTime() / 1000 ;
            long time = expiredTime  + 3600;
            System.out.println("expiredTime:" + expiredTime + ",time:" + time);

            System.out.println("expiredTime-length:" + (expiredTime + "").length() + ";time-length:" + (time + "").length());
        }

    }
}
