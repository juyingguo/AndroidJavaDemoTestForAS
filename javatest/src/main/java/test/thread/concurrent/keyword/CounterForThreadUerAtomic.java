package test.thread.concurrent.keyword;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterForThreadUerAtomic {
 
    public static AtomicInteger aiCcount = new AtomicInteger();

    public synchronized static void inc() {
 
        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
 
        aiCcount.addAndGet(1);
    }
 
    public static void main(String[] args) {
 
        //同时启动1000个线程，去进行i++计算，看看实际结果
 
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CounterForThreadUerAtomic.inc();
                }
            }).start();
        }
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:aiCcount=" + CounterForThreadUerAtomic.aiCcount.get());
    }
}