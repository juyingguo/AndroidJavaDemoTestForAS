package com.javareflect.one;
/*
Java面试题之反射
		https://blog.csdn.net/ys_230014/article/details/88795929
 */
public class JavaReflectTest01 {
    public static void main( String args[] ){
        test01();
    }

    private static void test01() {
        Class class01 = Student.class;
        System.out.println("class01:" + class01);

        Class class02 = new Student().getClass();
        System.out.println("class02:" + class02);

        try {
            Class class03 = Class.forName("com.javareflect.one.Student");
            System.out.println("class03:" + class03);
            System.out.println("class01=class03:" + (class01==class03));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
