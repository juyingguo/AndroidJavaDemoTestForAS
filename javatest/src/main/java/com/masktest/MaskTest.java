package com.masktest;

public class MaskTest {
    public static void main(String[] args) {
        test01(10);
        test02();

    }

    private static void test01(int a) {

        int b = -1;
        int n = 1;
        int m = 4;

        System.out.println("" + (b & n));
        System.out.println("" + (b & m));



    }
    private static void test02() {
        String[] array = new String[]{"a","b"};
        array = null;
        for (String a: array ) {
            System.out.println(a);
        }
    }
}
