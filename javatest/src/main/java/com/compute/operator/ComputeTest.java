package com.compute.operator;

public class ComputeTest {
    public static void main(String[] args) {
        test01();
        test02();

    }

    private static void test01() {

        int a = 10;
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
