package com.yunsuanfu;

import com.utils.BinaryUtils;

public class YunSuanFuTest01 {


    public static void main(String[] args) {
        test01();
    }

    private static void test01() {


        int sn = 0;
        int a = ~sn;
        System.out.println("sn:" + sn);
        System.out.println("toBinaryString(sn):" + BinaryUtils.toBinaryString(sn));
        System.out.println("a:" + a);
        System.out.println("toBinaryString(a):" + BinaryUtils.toBinaryString(a));



    }

}
