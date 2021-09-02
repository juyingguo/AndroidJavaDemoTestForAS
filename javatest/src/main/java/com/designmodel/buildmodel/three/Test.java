package com.designmodel.buildmodel.three;
/**
 * Java8ThisTest.java
 *  测试类
 */
public class Test {
    public static void main(String[] args) {
//         ConcreteBuilder concreteBuilder = new ConcreteBuilder();//写法1 ok
         Builder builder = new ConcreteBuilder();//写法2 ok
         Product pt = builder
                .bulidA("牛肉煲")
                .bulidC("全家桶")
                .bulidD("西瓜")
                .build();
        System.out.println(pt.getBuildA());
        System.out.println(pt.getBuildB());
        System.out.println(pt.getBuildC());
        System.out.println(pt.getBuildD());
    }
}