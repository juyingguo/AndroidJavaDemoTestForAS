package com.java8test;

import java.util.List;
import java.util.ArrayList;
 
public class Java8MethodRefTester {
   public static void main(String args[]){
      List<String> names = new ArrayList();
        
      names.add("Google");
      names.add("Runoob");
      names.add("Taobao");
      names.add("Baidu");
      names.add("Sina");
        
      names.forEach(System.out::println);
      System.out.println();
   }
}