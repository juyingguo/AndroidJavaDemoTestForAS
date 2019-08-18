package com.javareflect.two;
 
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 
/**
*
* @ClassName: Demo3
* @Description: 动态调用方法
* @author LQ
* @date 2019年2月22日 下午8:53:08
*
*/
public class Demo3 {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        // 一切反射相关的代码都从获得类对象开始
        Class clz = Student.class;
 
        // 第一种，调无参的
        //// 第一个参数：方法名
        //// 第二个参数：参数的类型
//         Method method = clz.getMethod("hello");
        //// 第一个参数：具体的类的类实例
        //// 第二个参数：具体的参数值
//         method.invoke(clz.newInstance());
 
 
//        第二种，调有参的
//        Method method = clz.getMethod("hello", String.class);
//        method.invoke(clz.newInstance(), "有点意思");
        
        
//        method.invoke( )返回值问题
//        如果说，被调用的方法，本身是void类型，那么method.invoke( )返回值就是null
//        如果说，被调用的方法，本身是非void类型，那么method.invoke( )返回值就是被调用的方法的返回值
        Method method =  clz.getDeclaredMethod("add",Integer.class,Integer.class);
        method.setAccessible(true);
        Object invoke = method.invoke(clz.newInstance(),45,56);
        System.out.println(invoke);
    }
}