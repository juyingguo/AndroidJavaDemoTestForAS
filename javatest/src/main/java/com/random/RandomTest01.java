package com.random;

import java.util.ArrayList;
import java.util.Random;

public class RandomTest01 {


    public static void main(String[] args) {
        test01();
        test02();
    }

    private static void test01() {

        for(int i = 0 ;i<10;i++){
            int sn = new Random().nextInt(Integer.MAX_VALUE);
            System.out.println("sn:" + sn);

        }

    }
    private static void test02() {

        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayListCopy = new ArrayList<>();
        for(int i = 0 ;i<10000 * 100;i++){
            int sn = new Random().nextInt(Integer.MAX_VALUE);
            arrayList.add(sn);
            arrayListCopy.add(sn);
//            System.out.println("sn:" + sn);

        }
        for (Integer a : arrayList){
            for (Integer b : arrayListCopy){
                if (a == b){
                    System.out.println("a:" + a + ",b:" + b);

                }
                if (b <100){
                    System.out.println("b:" + b );

                }


            }
        }

    }
}
