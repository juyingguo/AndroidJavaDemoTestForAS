package com.threadtest.volatiletest;

/**
 * Date:2019/8/3,11:23
 * author:jy
 */
public class VolatileTest02 {
    volatile int a = 1;
    volatile boolean ready;
    private String ACTION_ENTER_GAME_COURSE_MODE = "ACTION_ENTER_GAME_COURSE_MODE" ;
    private String ACTION_EXIT_GAME_COURSE_MODE = "ACTION_EXIT_GAME_COURSE_MODE";
    private String ACTION_ASR_RESULT_FOR_GAME_COURSE_MODE = "ACTION_ASR_RESULT_FOR_GAME_COURSE_MODE";

    private String EXTRA_ASR_RESULT = "EXTRA_ASR_RESULT";

    public class PrintA extends Thread{
        @Override
        public void run() {
            while(!ready){
                Thread.yield();
            }
            System.out.println(a);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        VolatileTest02 t = new VolatileTest02();
        t.new PrintA().start();
        //下面两行如果不加volatile的话，执行的先后顺序是不可预测的。并且下面两行都是原子操作，但是这两行作为一个整体的话就不是一个原子操作。
        t.a = 48; //这是一个原子操作，但是其结果不一定具有可见性。加上volatile后就具备了可见性。
        t.ready = true;//同理
    }
/*---------------------
    作者：褚金辉
    来源：CSDN
    原文：https://blog.csdn.net/maosijunzi/article/details/18315013
    版权声明：本文为博主原创文章，转载请附上博文链接！*/
}
