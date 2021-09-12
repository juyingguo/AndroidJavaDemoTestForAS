package com.classtest;

public class ClassMethodAssignableTest {
    public static void main(String[] args) {
        System.out.println(Student.class.isAssignableFrom(BasePerson.class));
        System.out.println(BasePerson.class.isAssignableFrom(Student.class));

        BasePerson basePerson = new BasePerson();
        Student student = new Student();

        System.out.println(basePerson.getClass());
        System.out.println(student.getClass());

        System.out.println(student.getClass().isAssignableFrom(basePerson.getClass()));
        System.out.println(basePerson.getClass().isAssignableFrom(student.getClass()));
        System.out.println(Object.class.isAssignableFrom(student.getClass()));
    }
    static class BasePerson{

    }
    private static class Student extends BasePerson{

    }
}
