package com.threadtest.synchronizedtest;

/**
 * Date:2019/8/2,9:58
 * author:jy
 */
public class Demo2 {
    /*
     *
     ---------------------
     作者：真快啊夏天
     来源：CSDN
     原文：https://blog.csdn.net/sinat_32588261/article/details/72880159
     版权声明：本文为博主原创文章，转载请附上博文链接！
     */


    public static void main(String args[]){
//test01
//		SyncThread s1 = new SyncThread();
//		SyncThread s2 = new SyncThread();
//		Thread t1 = new Thread(s1);
//		Thread t2 = new Thread(s2);
//test02
        SyncThread s = new SyncThread();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);

        t1.start();
        t2.start();
    }
}
class SyncThread implements Runnable {
//    private static int count;
    /**
     * 精确而优雅： 2）当一个线程访问对象的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该对象中的非synchronized(this)同步代码块 这个是因为你把count变量声明为静态，全局变量肯定是对应一个。去掉static就test01就和下面的不一样了
     */
    private  int count;

    public SyncThread() {
        count = 0;
    }

    public  void run() {
        synchronized(this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount() {
        return count;
    }
}