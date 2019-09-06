package com.methodtest;

public class MethodTest {
    public static void main(String[] args) {
        test01(10);
        test02();

    }

    private static void test01(int a) {

        a = 1;
        a -=5;
        System.out.println("test01,a=" + a);
    }
    private static void test02() {
        long waitNanos = 10*1000000L;
        long waitMillis = waitNanos / 1000000L;
        waitNanos -= (waitMillis * 1000000L);
        System.out.println("test01,waitNanos=" + waitNanos);
    }
}
