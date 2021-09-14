package com.classtest;

/**
 * Date:2021/9/10,10:09
 * author:jy
 * <p>
 *   test for {@link Class#getSuperclass()}
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

        testSuperclassForInterface();
    }

    private static void testSuperclassForInterface() {

        MyTestInterfaceImpl myTestInterface = new MyTestInterfaceImpl();
        System.out.println(myTestInterface);
        Class<?> superclass = myTestInterface.getClass().getSuperclass();
        Class<?>[] interfaces = myTestInterface.getClass().getInterfaces();
        if (interfaces !=null && interfaces.length >0){
            System.out.println("interfaces[0]:" + interfaces[0]);
            Class<?> superclass1 = interfaces[0].getSuperclass();
            System.out.println(superclass1);
        }
        System.out.println(superclass);
        if (superclass != null) {
            Class<?> superclass1 = superclass.getSuperclass();
            System.out.println(superclass1);
        }

    }

    interface TestInterface{

    }

    static class MyTestInterfaceImpl implements TestInterface{}
}
