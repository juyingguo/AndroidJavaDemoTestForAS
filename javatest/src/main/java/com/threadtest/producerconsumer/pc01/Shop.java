package com.threadtest.producerconsumer.pc01;

import java.util.ArrayList;
import java.util.List;
 
/**
 * Created with IDEA.
 * User:haibo.
 * DATE:2018/7/22/022
 */
public class Shop {
    List<String> data = new ArrayList<String>();
    public void produce(){
        synchronized (data) {
            if(data.size()>0){
                System.out.println("可以卖面包了");
                data.notify();
                try {
                    data.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            String a = "面包"+System.currentTimeMillis();
            data.add(a);
            System.out.println("生成了："+a+",找人来吃，现在面包个数为："+data.size());
            //data.notify();
        }
 
    }
 
    public void sale(){
        synchronized (data) {
            if(data.size()==0){
                try {
                    System.out.println("没面包了该通知做面包了");
                    data.notify();
                    data.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            String s = data.get(0);
            data.remove(0);
            System.out.println("吃掉了面包"+s);
            data.notify();
        }
 
    }
 
    public static void main(String[] args) {
        Shop shop = new Shop();
        Thread productor = new Thread(new Productor(shop));
        Thread customer = new Thread(new Customer(shop));
        productor.start();
        customer.start();

 
    }
}
class Productor implements Runnable{
    Shop shop;
 
    public Productor(Shop shop) {
        super();
        this.shop = shop;
    }
 
    @Override
    public void run() {
        while(true){
            shop.produce();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // TODO Auto-generated method stub
 
    }
}
class Customer implements Runnable {
    Shop shop;

    public Customer(Shop shop) {
        super();
        this.shop = shop;
    }

    @Override
    public void run() {

        while (true) {
            shop.sale();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

    }
}
/*

--------------------- 
作者：你嘎哈呢 
来源：CSDN 
原文：https://blog.csdn.net/lettyisme/article/details/81174733 
版权声明：本文为博主原创文章，转载请附上博文链接！*/
