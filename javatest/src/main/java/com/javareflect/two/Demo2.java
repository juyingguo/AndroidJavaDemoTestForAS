package com.javareflect.two;
 
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
 
/**
*
* @ClassName: Demo2
* @Description: 实例化对象
* @author LQ
* @date 2019年2月22日 下午8:25:45
*
*/
public class Demo2 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException,
            SecurityException, IllegalArgumentException, InvocationTargetException {
        // 一切反射相关的代码都从获得类对象开始
        Class clz = Student.class;
        // 所有的类默认提供了一个无参构造器（以后创建类，提供了有参构造器后要提供无参构造器）
        // clz.newInstance();
        // -->
        // Constructor c= clz.getConstructor();
        // Object newInstance = c.newInstance();
 
 
        // 调用带一个参数的构造方法创建了一个学生对象
        // Constructor = Student (String.class)传参数
//         Constructor c= clz.getConstructor(String.class);
//         c.newInstance("哈哈");
 
 
        // 调用带二个参数的构造方法创建了一个学生对象
//         Constructor c= clz.getConstructor(String.class,String.class);
//         c.newInstance("哈哈","SDSDS");
 
 
        // getDeclaredConstructor与Constructor的区别
        // Constructor：只能拿到OOP思想能实例化的构造器
        // getDeclaredConstructor：只要是个构造器都能拿到
        Constructor c = clz.getDeclaredConstructor(Integer.class);
        // 访问私有构造器时，要打开它的权限
        c.setAccessible(true);
        c.newInstance(45);

            //Constructor 无法获取私有的构造器。
//        Constructor c = clz.getConstructor(Integer.class);
        // 访问私有构造器时，要打开它的权限
//        c.setAccessible(true);
//        c.newInstance(45);


    }
}