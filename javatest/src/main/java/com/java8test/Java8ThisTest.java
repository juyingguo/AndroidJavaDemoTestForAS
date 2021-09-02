package com.java8test;

/**
 * @author Jly
 * @date 2019/3/26  14:58
 * https://blog.csdn.net/weixin_33875839/article/details/92415798
 */
public class Java8ThisTest {
 
    protected interface FunctionEx {
        void apply(String a);
    }
 
    public void adddd(String a){
        System.out.println("adddd---" + a);
        add(a,this::doAdd);
    }
 
    public String doAdd(String a){
        System.out.println("doAdd---" + a);
        return "";
    }
 
    public void add(String a,FunctionEx functionEx){
        System.out.println("add---" + a);
        functionEx.apply(a);  // 其实这里执行的就是doAdd
    }
 
    public static void main(String[] args) {
        Java8ThisTest test = new Java8ThisTest();
        System.out.println("main---");
        test.adddd("Jly");
    }
}