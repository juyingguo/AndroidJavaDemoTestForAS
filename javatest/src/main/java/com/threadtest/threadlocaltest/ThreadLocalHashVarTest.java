package com.threadtest.threadlocaltest;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date:2021/9/14,10:43
 * author:jy
 */
public class ThreadLocalHashVarTest {


    /**
     * The next hash code to be given out. Updated atomically. Starts at
     * zero.
     */
    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    private final int threadLocalHashCode = nextHashCode();

    /**
     * The difference between successively generated hash codes - turns
     * implicit sequential thread-local IDs into near-optimally spread
     * multiplicative hash values for power-of-two-sized tables.
     */
    private /*static*/ final int HASH_INCREMENT = 0x61c88647;

    /**
     * Returns the next hash code.
     */
    private /*static*/ int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    public static void main(String[] args) {
        ThreadLocalHashVarTest hashVarTest = new ThreadLocalHashVarTest();
//        System.out.println(ThreadLocalHashVarTest.HASH_INCREMENT);
        System.out.println(hashVarTest.threadLocalHashCode);
        System.out.println(hashVarTest.threadLocalHashCode);
        System.out.println(hashVarTest.threadLocalHashCode);
    }
    @Test
    public void test(){
        ThreadLocalHashVarTest hashVarTest = new ThreadLocalHashVarTest();
//        System.out.println(ThreadLocalHashVarTest.HASH_INCREMENT);
        System.out.println(hashVarTest.threadLocalHashCode);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(hashVarTest.threadLocalHashCode);
        System.out.println(hashVarTest.threadLocalHashCode);
    }
}
