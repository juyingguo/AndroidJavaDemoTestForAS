package com.javageneric;

import java.util.ArrayList;
import java.util.List;

public class GenericSuperTest {
     
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        List<Integer> age = new ArrayList<>();
        List<Number> number = new ArrayList<>();
        List<Byte> byteList = new ArrayList<>();

        name.add("icon");
        age.add(18);
        number.add(205);
        byteList.add(Byte.parseByte("50"));

//        getSuperNumber(name);//1
        getSuperNumber(age);//2
        getSuperNumber(number);//3
//        getSuperNumber(byteList);//4

        getData(name);
        getData(age);
        getData(number);
        getData(byteList);

   }
 
   public static void getData(List<?> data) {
      System.out.println("data :" + data.get(0));
   }
   /*public static void getData(List<?> data) {
        System.out.println("data :" + data.get(0));
    }*/

//    public static int getData2(List<T> data) {
//        return data.size();
//    }
   
   public static void getSuperNumber(List<? super Integer> data) {
          System.out.println("data :" + data.get(0));
       }
}