package com.utils;

public class ArrayUtils {
    public static void printArray(int[] a) {
        if (a != null || a.length>0){
            for (int i = 0; i <a.length; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }
    }
}
