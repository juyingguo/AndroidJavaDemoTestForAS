package com.java8test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8StreamTester1 {
   public static void main(String args[]){
      User user1 = new User("张三", "女", 1);
      User user2 = new User("李四", "男", 2);
      User user3 = new User("张三", "女", 3);
      List<User> list = Arrays.asList(user1, user2, user3);

      /**
       * 1、获取年龄大于2的对象
       */
      List<User> collect = list.stream().filter(x -> x.getAge() > 2).collect(Collectors.toList());
      System.out.println("获取年龄大于2的数量 = " + collect.size());
      //输出：获取年龄大于2的数量 = 1

      /**
       * 2、去重 设置name相同即为相同对象
       */
      //方式1直接使用 distinct
      List<User> collect1 = list.stream().distinct().collect(Collectors.toList());
      System.out.println("输出剩余对象" + collect1);
      //输出：输出剩余对象[User(name=张三, sex=女, age=1), User(name=李四, sex=男, age=2)]

      /**
       * 3、从集合找出与该对象相同的元素 同样name相同即为相同对象
       */
      User user4 = new User("张三", "男", 8);
      Predicate<User> predicate =  Predicate.isEqual(user4);
      List<User> collect2 = list.stream().filter(predicate).collect(Collectors.toList());
      System.out.println("与该对象相同的对象有" + collect2);
      //输出：与该对象相同的对象有[User(name=张三, sex=女, age=1), User(name=张三, sex=女, age=3)]
   }
   private static class User {
      public User(String name, String sex, Integer age) {
         this.name = name;
         this.sex = sex;
         this.age = age;
      }

      /**
       * 姓名
       */
      private String name;

      /**
       * 性别
       */
      private String sex;

      /**
       * 年龄
       */
      private Integer age;

      public void setAge(Integer age) {
         this.age = age;
      }

      public Integer getAge() {
         return age;
      }

      /**
       * 重写equals和hashCode
       */
      @Override
      public boolean equals(Object obj) {
         if (obj instanceof User) {
            User user = (User) obj;
            if (name.equals(user.name)){
               return true;
            }
         }
         return false;
      }
      @Override
      public int hashCode () {
         return name.hashCode();
      }

      @Override
      public String toString() {
         return "User{" +
                 "name='" + name + '\'' +
                 ", sex='" + sex + '\'' +
                 ", age=" + age +
                 '}';
      }
   }
}