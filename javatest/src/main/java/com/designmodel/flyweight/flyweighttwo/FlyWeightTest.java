package com.designmodel.flyweight.flyweighttwo;

import org.junit.Test;

/**
 * Date:2021/11/8,16:38
 * author:jy
 */
public class FlyWeightTest {
    @Test
    public void test(){
        FlyWeight fly1 = FlyWeightFactory.getFlyWeight("a");
        System.out.println("fly1.extrinsic:" + fly1.extrinsic);
        System.out.println(fly1.getIntrinsic());
        fly1.setIntrinsic("a1");
        System.out.println(fly1.getIntrinsic());
        FlyWeight fly2 = FlyWeightFactory.getFlyWeight("a");
        System.out.println("fly2.extrinsic:" + fly2.extrinsic);
        System.out.println(fly2.getIntrinsic());
        System.out.println(fly1 == fly2);

        FlyWeight fly3 = FlyWeightFactory.getFlyWeight("b");
        System.out.println("fly3.extrinsic:" + fly3.extrinsic);
        System.out.println(fly3.getIntrinsic());

        FlyWeight fly4 = FlyWeightFactory.getFlyWeight("c");
        System.out.println("fly4.extrinsic:" + fly4.extrinsic);
        System.out.println(fly4.getIntrinsic());

        FlyWeight fly5 = FlyWeightFactory.getFlyWeight("d");
        System.out.println("fly5.extrinsic:" + fly5.extrinsic);
        System.out.println(fly5.getIntrinsic());

        System.out.println(FlyWeightFactory.getSize());
    }
}
