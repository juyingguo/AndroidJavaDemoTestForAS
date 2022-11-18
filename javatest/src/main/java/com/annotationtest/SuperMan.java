package com.annotationtest;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jy
 * @date 2022/11/10,11:00
 * @description:
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Persons {
    Person[]  value();
}
@Repeatable(Persons.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Person{
    String role() default "";
}
@Person(role="artist")
@Person(role="coder")
@Person(role="PM")
@Deprecated
public class SuperMan {

    public static void main(String[] args) {
        boolean hasAnnotation = SuperMan.class.isAnnotationPresent(Person.class);
        /*if (hasAnnotation){
            Annotation[] annotations = SuperMan.class.getAnnotations();
            for (Annotation annotation : annotations){
                if (annotation instanceof  Person){
                    Person person = (Person) annotation;
                    System.out.println("person.role:" + person.role());

                }
            }
        }*/
        if (hasAnnotation){
            Person person = SuperMan.class.getAnnotation(Person.class);
            System.out.println("person.role:" + person.role());
        }
    }
    @Test
    public void testForOneAnnotation() {
        boolean hasAnnotation = SuperMan.class.isAnnotationPresent(Person.class);
        if (hasAnnotation){
            Person person = SuperMan.class.getAnnotation(Person.class);
            System.out.println("person.role:" + person.role());
        }
    }
    @Test
    public void testForMultipleAnnotation() {


//        boolean hasAnnotation = SuperMan.class.isAnnotationPresent(Person.class);//SuperMan添加多个注解Person.class，使用这种方式获取的值为false
        boolean hasAnnotation = SuperMan.class.isAnnotationPresent(Person.class);//SuperMan添加多个注解Person.class，使用这种方式获取的值为false
        if (hasAnnotation){
            Person[] persons = SuperMan.class.getAnnotationsByType(Person.class);
            for (Person person: persons) {
                System.out.println("person.role:" + person.role());

            }
        }
    }
    @Test
    public void testForMultipleAnnotation2() {

        Person[] persons = SuperMan.class.getAnnotationsByType(Person.class);//SuperMan添加多个注解Person.class，可以直接使用这种方式获取
        for (Person person: persons) {
            System.out.println("person.role:" + person.role());

        }
    }
    @Test
    public void testForMultipleAnnotation3() {

        Person[] persons = SuperMan.class.getDeclaredAnnotationsByType(Person.class);
        for (Person person: persons) {
            System.out.println("person.role:" + person.role());

        }
    }

    @Test
    public void testForMultipleAnnotation4() {
        //SuperMan添加多个注解Person.class
        Annotation[] annotations = SuperMan.class.getAnnotations();
        System.out.println("annotations.length:" + annotations.length);//无论SuperMan上有多少个注解Person.class，打印中的个数其中Person.class的计数都是一个

        for (Annotation annotation : annotations){
//            if (annotation instanceof  Person){
//                Person person = (Person) annotation;
//                System.out.println("person.role:" + person.role());
//
//            }
            System.out.println("annotation.getClass().getSimpleName():" + annotation.getClass().getSimpleName());//annotation.getClass().getSimpleName():$Proxy2
        }
    }
}