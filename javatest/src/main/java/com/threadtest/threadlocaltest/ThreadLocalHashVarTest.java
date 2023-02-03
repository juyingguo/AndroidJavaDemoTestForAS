package com.threadtest.threadlocaltest;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date:2021/9/14,10:43
 * author:jy
 */
public class ThreadLocalHashVarTest<T> {


    /**
     * The next hash code to be given out. Updated atomically. Starts at
     * zero.
     */
    private static AtomicInteger nextHashCode = new AtomicInteger();

    private /*final*/ int threadLocalHashCode = nextHashCode();
    private final int hash = nextHashCode.getAndAdd(0x61c88647 * 2);
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

    /**
     * 中间再通过函数访问，结果是同一个
     */
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
    /**
     * 中间再通过函数访问，结果是同一个
     */
    @Test
    public void testTwoThread(){
        ThreadLocalHashVarTest hashVarTest = new ThreadLocalHashVarTest();
        new Thread("thread-1"){
            @Override
            public void run() {
                super.run();
                System.out.println("thread-1:" + hashVarTest.threadLocalHashCode);
                System.out.println("thread-1:" + hashVarTest.threadLocalHashCode);
            }
        }.start();
        new Thread("thread-2"){
            @Override
            public void run() {
                super.run();
                System.out.println("thread-2:" + hashVarTest.threadLocalHashCode);
                System.out.println("thread-2:" + hashVarTest.threadLocalHashCode);
            }
        }.start();
    }

    /**
     * 直接访问变量，每次结果不同。和预期一样。
     */
    @Test
    public void testOperateVariable(){
        ThreadLocalHashVarTest hashVarTest = new ThreadLocalHashVarTest();
//        System.out.println(ThreadLocalHashVarTest.HASH_INCREMENT);
        System.out.println(hashVarTest.nextHashCode.getAndAdd(HASH_INCREMENT));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(hashVarTest.nextHashCode.getAndAdd(HASH_INCREMENT));
        System.out.println(hashVarTest.nextHashCode.getAndAdd(HASH_INCREMENT));
    }
    @Test
    public void testOperateVariableHash(){
        ThreadLocalHashVarTest hashVarTest = new ThreadLocalHashVarTest();
//        System.out.println(ThreadLocalHashVarTest.HASH_INCREMENT);
        System.out.println(hashVarTest.hash);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(hashVarTest.hash);
    }
    @Test
    public void testOperateVariableHashCreateTwoObject(){
        ThreadLocalHashVarTest hashVarTest = new ThreadLocalHashVarTest();
        ThreadLocalHashVarTest hashVarTestB = new ThreadLocalHashVarTest();
//        System.out.println(ThreadLocalHashVarTest.HASH_INCREMENT);
        System.out.println(hashVarTest.hash);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(hashVarTest.hash);
        System.out.println(hashVarTestB.hash);
        System.out.println(hashVarTestB.hash);
    }
    @Test
    public void testOperateVariableHashCreateTwoObject2(){

        MySelfLooper looper = new MySelfLooper();
        MySelfLooper looper2 = new MySelfLooper();
        System.out.println(looper.sThreadLocal.hash);
        System.out.println(looper.sThreadLocal.hash);
        System.out.println(looper2.sThreadLocal.hash);
        System.out.println(looper2.sThreadLocal.hash);
    }
}
