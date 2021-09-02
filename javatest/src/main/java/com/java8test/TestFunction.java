package com.java8test;

import java.util.function.Function;

/**
 * Date:2021/8/18,14:21
 * author:jy
 */
public class TestFunction {
    private static void testFunction(int basic, Function<Integer, Integer> function) {
        int value = function.apply(basic);
        System.out.print(value + "\n");
    }

    public static void main(String... args) {
        testFunction(1, val -> val + 1000);
        testFunction(5, val -> val * 100);
        testFunction(5, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 10;
            }
        });
    }
}
