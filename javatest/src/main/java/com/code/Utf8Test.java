package com.code;

import com.utils.UTF8Util;

public class Utf8Test {
    public static void main(String[] args) {
        test1();
        test2();
        test3();


    }

    private static void test1() {
        String s1 = "好";
        System.out.println("s1.length:" + s1.length());

        String s2 = UTF8Util.encode(s1);

        System.out.println("s2.length:" + s2.length());
    }
    private static void test2() {
        String s1 = "a";
        System.out.println("test2>()>s1.length:" + s1.length());

        String s2 = UTF8Util.encode(s1);

        System.out.println("test2>()>>s2.length:" + s2.length());
    }
    private static void test3() {
        String s1 = "，";
        System.out.println("test3>()>s1.length:" + s1.length());

        String s2 = UTF8Util.encode(s1);

        System.out.println("test3>()>>s2.length:" + s2.length());
    }
}
