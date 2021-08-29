package com.annotationtest;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Check {
    String value();
}
@Retention(RetentionPolicy.RUNTIME)
@interface Perform {}

class Hero {
    @Deprecated
    public void say(){
        System.out.println("Noting has to say!");
    }
    void speak(){
        System.out.println("I have a dream!");
    }
}

@TestAnnotation(msg="hello")
public class TestCheckAnnotation {
    @Check(value="hi")
    public int a;
    @Perform
    private void testMethod(){}

    //@SuppressWarnings(value = "deprecation")
    @SuppressWarnings({"deprecation"})
    public void test1(){
        Hero hero = new Hero();
        hero.say();
        hero.speak();
    }
    public static void main(String[] args) {
        boolean hasAnnotation = TestCheckAnnotation.class.isAnnotationPresent(TestAnnotation.class);
        if ( hasAnnotation ) {
            TestAnnotation testAnnotation = TestCheckAnnotation.class.getAnnotation(TestAnnotation.class);
            //获取类的注解
            System.out.println("id:"+testAnnotation.id());
            System.out.println("msg:"+testAnnotation.msg());
        }
        try {
            Field a = TestCheckAnnotation.class.getDeclaredField("a");
            a.setAccessible(true);
            //获取一个成员变量上的注解
            Check check = a.getAnnotation(Check.class);
            if (check != null ) {
                System.out.println("check value:"+check.value());
            }
            Method testMethod = TestCheckAnnotation.class.getDeclaredMethod("testMethod");
            if (testMethod != null ) {
                // 获取方法中的注解
                Annotation[] ans = testMethod.getAnnotations();
                for( int i = 0;i < ans.length;i++) {
                    System.out.println("method testMethod annotation:"+ans[i].annotationType().getSimpleName());
                }
            }
        } catch (NoSuchFieldException | SecurityException | NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}