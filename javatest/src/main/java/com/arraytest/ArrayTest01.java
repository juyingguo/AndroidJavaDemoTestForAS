package com.arraytest;

import com.utils.BinaryUtils;

public class ArrayTest01 {


    public static void main(String[] args) {
        test01();
    }
    static Object[] mBaseCache = new Object[1];
    static Object[] array = new Object[2];
    static int[] hashes = new int[1];
    private static void test01() {


        int sn = 0;
        int a = ~sn;
        System.out.println("sn:" + sn);
        System.out.println("toBinaryString(sn):" + BinaryUtils.toBinaryString(sn));
        System.out.println("a:" + a);
        System.out.println("toBinaryString(a):" + BinaryUtils.toBinaryString(a));


        array[0] = mBaseCache;
        array[1] = hashes;

        mBaseCache = array;


    }


}
