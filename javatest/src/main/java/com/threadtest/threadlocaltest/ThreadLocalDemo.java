package com.threadtest.threadlocaltest;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * @author 一灯架构
 * @apiNote ThreadLocal示例
 **/
public class ThreadLocalDemo {
    // 1. 创建ThreadLocal
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    static ThreadLocal<Integer> threadLocalInteger = new ThreadLocal<>();
    static ThreadLocal<SimpleDateFormat> threadLocalDateFormat =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static void main(String[] args) {
        // 2. 给ThreadLocal赋值
        threadLocal.set("关注公众号:一灯架构");
        // 3. 从ThreadLocal中取值
        String result = threadLocal.get();
        System.out.println(result); // 输出 关注公众号:一灯架构
        
        // 4. 删除ThreadLocal中的数据
        threadLocal.remove();
        System.out.println(threadLocal.get()); // 输出null

    }
    @Test
    public void threadIsolateTest(){
        System.out.println(Thread.currentThread().getName()
                + "," + threadLocalInteger.get());
        IntStream.range(0, 5).forEach(i -> {
            // 创建5个线程，分别给threadLocal赋值、取值
            new Thread(() -> {
                // 2. 给ThreadLocal赋值
                threadLocalInteger.set(i);
                // 3. 从ThreadLocal中取值
                System.out.println(Thread.currentThread().getName()
                        + "," + threadLocalInteger.get());
            }).start();
        });
    }

    /**
     *
     */
    @Test
    public void threadIsolateDateFormatTest() throws InterruptedException {
        IntStream.range(0, 5).forEach(i -> {
            // 创建5个线程，分别从threadLocal取出SimpleDateFormat，然后格式化日期
            new Thread(() -> {
                try {
                    System.out.println(threadLocalDateFormat.get().parse("2022-11-11 00:00:00"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(threadLocalDateFormat.get().format(new Date()));
            }).start();
        });

        Thread.sleep(1000);
    }
    @Test
    public void gcTest() throws InterruptedException {
        // 2. 给ThreadLocal赋值
        threadLocal.set("关注公众号:一灯架构");
        // 3. 从ThreadLocal中取值
        String result = threadLocal.get();
        // 手动触发GC
        System.gc();
        System.out.println(result); // 输出 关注公众号:一灯架构

        Thread.sleep(1000);
    }
}