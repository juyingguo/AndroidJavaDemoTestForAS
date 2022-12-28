package com.java8test;

import org.junit.Test;

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

        Function<Integer, Integer> function6 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer param) {
                return param + 600;
            }
        };
        testFunction(6,function6);

        Function<Integer, Integer> function7 = param -> param + 700;
        testFunction(7,function7);
    }

    public int testCompose1(int a, Function<Integer, Integer> funA,
                                  Function<Integer, Integer> funB) {
        return funA.compose(funB).apply(a);
    }
    @Test
    public void testCompose11(){
        System.out.println("compose11,"+testCompose1(5, value -> value - 1,value -> value * 2));
    }

    public int testCompose2(int a, Function<Integer, Integer> funA,
                           Function<Integer, Integer> funB) {

        return funA.compose(funB).apply(a);
    }
    @Test
    public void testCompose22(){
        System.out.println("testCompose22,"+testCompose2(5, value -> value - 1,value -> value * 2));
    }
}
