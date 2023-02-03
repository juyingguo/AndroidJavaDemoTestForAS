package com.java8test;

import org.junit.Test;

public class Java8InterfaceDefaultMethodTest {
    @Test
    public void blowHorn(){
        Vehicle vehicle = new Car();
        vehicle.print();
        Vehicle vehicle2 = new CarB();
        vehicle2.print();
    }
}
interface Vehicle {
    default void print(){
        System.out.println("我是一辆车!");
    }

    //Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法
    static void blowHorn(){
        System.out.println("按喇叭!!!");
    }
}

interface FourWheeler {
    default void print(){
        System.out.println("我是一辆四轮车!");
    }
}
//个类实现了多个接口，且这些接口有相同的默认方法，以下实例说明了这种情况的解决方法
//第一个解决方案是创建自己的默认方法，来覆盖重写接口的默认方法：
class CarB implements Vehicle, FourWheeler {
    public void print(){
        System.out.println("CarB,我是一辆汽车!");
    }
}
//第二种解决方案可以使用 super 来调用指定接口的默认方法：
class Car implements Vehicle, FourWheeler {
    public void print(){
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }
}
