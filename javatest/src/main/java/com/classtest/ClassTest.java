package com.classtest;

/**
 * Date:2021/9/10,10:09
 * author:jy
 * <p>
 *
 * </p>
 */
public class ClassTest {
    public static void main(String[] args) {
        ClassTest classTest = new ClassTest();
        Class<? extends ClassTest> aClass = classTest.getClass();
        System.out.println(aClass.getName());
        System.out.println(aClass.getCanonicalName());
        Class<?> superclass = aClass.getSuperclass();
        System.out.println(superclass);
        if (superclass != null){
            Class<?> superclass1 = superclass.getSuperclass();
            System.out.println(superclass1);
        }
    }
}
