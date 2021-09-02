package com.java8test;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Date:2021/8/18,13:50
 * author:jy
 */
public class LambdaTestTwo {
    public static void main(String[] args) {
        lambda01();
        lambda02();
        lambda03();
    }

    /**
     * 实例方法引用 如果函数式接口的实现恰好可以通过调用一个实例方法来实现，那么就可以使用实例方法引用
     */
    private static void lambda01() {
        Consumer<String> ins1 = r -> System.out.println(r);
        ins1.accept("1");
        Consumer<String> ins2 =System.out::println;
        ins1.accept("2");
    }

    /**
     * 对象方法引用 抽象方法的第一个参数类型刚好是实例方法的类型，抽象方法剩余的参数恰好可以当做实例方法的参数。
     * 如果函数式接口的实现能由上面说的实例方法调用来实现的话，那么就可以使用对象方法的引用
     */
    private static void lambda02() {
        Function<BigDecimal,Double> fuc1= t->t.doubleValue();
        Double r1 = fuc1.apply(new BigDecimal("1.025"));
        System.out.println("lambda02(),r1:" + r1);

        Function<BigDecimal,Double> fuc2=BigDecimal::doubleValue;
        fuc2.apply(new BigDecimal("1.025"));


        BiFunction<BigDecimal, BigDecimal, BigDecimal> func3 = (x, y) -> x.add(y);
        BigDecimal r3 = func3.apply(new BigDecimal("1.025"), new BigDecimal("1.254"));
        System.out.println("lambda02(),r3:" + r3);
        BiFunction<BigDecimal, BigDecimal, BigDecimal> func4 = BigDecimal::add;
        func4.apply(new BigDecimal("1.025"), new BigDecimal("1.254"));
    }

    /**
     * 静态方法的引用 如果函数式接口的实现恰好可以通过调用一个静态方法来实现，那么就可以使用静态方法引用
     */
    private static void lambda03() {
        Consumer<String> c1 = r -> {
            int i = Integer.parseInt(r);
            System.out.println("i=" + i);
        };
        c1.accept("1");
        Consumer<String> c2 =Integer::parseInt;
        c2.accept("2");
    }

}
