package com.java8test;

import java.util.function.Consumer;

/**
 * Date:2021/8/18,14:34
 * author:jy
 */
public class ConsumerTest {
    public static void main(String[] args) {
        Consumer<String> consumer = content -> {
            System.out.println(content);
        };
        consumer.accept("hello, world");

        Consumer<String> consumer2 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println( s);
            }
        };

        consumer.accept("hello consumer2.");
    }
}
