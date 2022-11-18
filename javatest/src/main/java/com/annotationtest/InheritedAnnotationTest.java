package com.annotationtest;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author jy
 * @date 2022/11/10,13:41
 * @description:
 */
public class InheritedAnnotationTest {
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @interface Test {}
    @Test
    public class A {}
    public class B extends A {}

    @Test
    public  void test(){
        boolean hasAnnotation = B.class.isAnnotationPresent(Test.class);
        if (hasAnnotation){
            Test test = B.class.getAnnotation(Test.class);
            System.out.println("test != null:" + test != null);
        }
    }
}
