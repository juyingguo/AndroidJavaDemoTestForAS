package com.java8test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

public class Java8MethodRefTester {


   @Test
   public void test1(){
//      构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
      final CarA car = CarA.create( CarA::new );
      final CarA car2 = CarA.create( CarA::new );
      final List< CarA > cars = Arrays.asList( car,car2 );

//      静态方法引用：它的语法是Class::static_method，实例如下：
      cars.forEach( CarA::collide );
//      特定类的任意对象的方法引用：它的语法是Class::method实例如下：
      cars.forEach( CarA::repair );

//      特定对象的方法引用：它的语法是instance::method实例如下：
      final CarA police = CarA.create( CarA::new );
      cars.forEach( police::follow );
   }
   @Test
   public void test(){
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
class CarA {
   //Supplier是jdk1.8的接口，这里和lamda一起使用了
   public static CarA create(final Supplier<CarA> supplier) {
      return supplier.get();
   }

   public static void collide(final CarA car) {
      System.out.println("Collided " + car.toString());
   }

   public void follow(final CarA another) {
      System.out.println("Following the " + another.toString());
   }

   public void repair() {
      System.out.println("Repaired " + this.toString());
   }
}