package com.type;

/**
 * Java基本数据类型和byte数组相互转化 
 * @author liuyazhuang 
 * 
 */  
public class FloatTest {

    public static void main(String[] args) {
        test2();


    }

    private static void test2() {
        float v = 1080/800f;
        int n = (int)(1080/800f);
        System.out.println(v);
        System.out.println(n);
        float v1 = 4160/480f;
        int n1 = (int)(4160/480f);
        int roundV1 = Math.round(v1);
        System.out.println(v1);
        System.out.println(roundV1);
        System.out.println(n1);

    }
    private static void test1() {
        String huanLine = "\n";
        System.out.print(huanLine);

        System.out.println(huanLine.length());

        byte[] huanLineToBytes = huanLine.getBytes();

        System.out.println(huanLineToBytes.length);
        System.out.println(huanLineToBytes[0]);

        System.out.println(huanLineToBytes[0] == "\n".getBytes()[0]);
    }
} 