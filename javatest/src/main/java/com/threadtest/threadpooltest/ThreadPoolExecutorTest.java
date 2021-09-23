package com.threadtest.threadpooltest;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date:2021/9/23,16:18
 * author:jy
 */
public class ThreadPoolExecutorTest {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
    @Test
    public void test(){
        int c = ctl.get();
        System.out.println("c=" + c);
        System.out.println(RUNNING);
        System.out.println(CAPACITY);
        System.out.println(workerCountOf(c));

        ctl.compareAndSet(c, c + 1);
        c = ctl.get();
        System.out.println("c=" + c);
        System.out.println(runStateOf(c));
    }
}
