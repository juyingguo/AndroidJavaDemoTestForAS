package com.simpledatatype;

import java.util.Arrays;

/**
 * Date:2020/4/15,9:57
 * author:jy
 */
public class IntegerAutoIncrementWithArrayTest {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(120);
                        test();


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private static int i = 0;
    private static final int MAX_NUM = 6;
    private static float[] arrayA = new float[MAX_NUM];
    private static void test() {
        System.out.println("test(),i:" + i);
        if (i == MAX_NUM){
            Arrays.sort(arrayA);
            printArray(arrayA);

            System.out.println("arrayA[" + (MAX_NUM -1)+ "] : " + arrayA[MAX_NUM - 1]);
            i = 0;
        }else {
            arrayA[i++] = (i+10);
        }
    }

    private static void printArray(float[] arrayA) {
        System.out.println("arrayA.length : " + arrayA.length);
        for(int j = 0 ; j<arrayA.length ; j++){
            System.out.println("printArray[" +j+ "] : " + arrayA[j]);
        }

    }


}
