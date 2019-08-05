package com.threadtest.synchronizedtest;

/**
 * Date:2019/8/2,11:40
 * author:jy
 */
public class ClassTest {
    public static void main(String args[]){
        test();
    }

    private static void test() {
        ClassA ca = new ClassA();
        ClassA cB = new ClassA();
        System.out.println("ca:" + ca);
        System.out.println("ca.getClass():" + ca.getClass());
        System.out.println("ca.getClass().hashCode():" + ca.getClass().hashCode());
        System.out.println("ClassA.class:" + ClassA.class);
        System.out.println("ClassA.class.hashCode():" + ClassA.class.hashCode());
        System.out.println("ca.getClass() == ClassA.class:" + (ca.getClass() == ClassA.class));
        System.out.println("ca.getClass().equals(ClassA.class):" + (ca.getClass().equals(ClassA.class)));

        System.out.println("cB------------------------:");
        System.out.println("cB:" + cB);
        System.out.println("cB.getClass():" + cB.getClass());
        System.out.println("cB.getClass().hashCode():" + cB.getClass().hashCode());
        System.out.println("ClassA.class:" + ClassA.class);
    }
}

class  ClassA {
    String name;
}
