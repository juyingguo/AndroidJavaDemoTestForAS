package com.compute;

/**
 * Date:2019/7/25,16:09
 * author:jy
 */
public class ApproximateValueTest {
    public static void main(String[] args) {
        test01();
        testRound();
        testFloat();
        testCeil();
    }

    private static void testCeil() {
        float d = 100.675f;
        System.out.println("testCeil，d=" + d + ",n=" + Math.ceil(d));
    }

    private static void testFloat() {
        float d = 100.675f;
        System.out.println("testFloat，d=" + d + ",n=" + Math.floor(d));
    }

    private static void testRound() {

        float d = 100.675f;
        System.out.println("testRound，d=" + d + ",n=" + Math.round(d));
        d = 100.500f;
        System.out.println("testRound，d=" + d + ",n=" + Math.round(d));
        d = 100;
        System.out.println("testRound，d=" + d + ",n=" + Math.round(d));
        d = 90f;
        System.out.println("testRound，d=" + d + ",n=" + Math.round(d));
        d = 90.1f;
        System.out.println("testRound，d=" + d + ",n=" + Math.round(d));

        d = -10.1f;
        System.out.println("testRound，d=" + d + ",n=" + Math.round(d));
        d = -10.6f;
        System.out.println("testRound，d=" + d + ",n=" + Math.round(d));
        d = -11.5f;
        System.out.println("testRound，d=" + d + ",n=" + Math.round(d));


    }

    private static void test01() {

        float f = 10.21f;
        long n = (long) f;
        System.out.println("test01,f=" + f + ",n=" + n);


        f = 10.50f;
        n = (long) f;
        System.out.println("test01,f=" + f + ",n=" + n);

        f = 10.51f;
        n = (long) f;
        System.out.println("test01,f=" + f + ",n=" + n);

        f = 10.55f;
        n = (long) f;
        System.out.println("test01,f=" + f + ",n=" + n);

        f = 10.59f;
        n = (long) f;
        System.out.println("test01,f=" + f + ",n=" + n);

        f = 10.60f;
        n = (long) f;
        System.out.println("test01,f=" + f + ",n=" + n);

        f = 10.61f;
        n = (long) f;
        System.out.println("test01,f=" + f + ",n=" + n);

        f = 10.65f;
        n = (long) f;
        System.out.println("test01,f=" + f + ",n=" + n);

        f = 10.69f;
        n = (long) f;
        System.out.println("test01,f=" + f + ",n=" + n);
    }

}
