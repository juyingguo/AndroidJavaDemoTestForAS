package com.simpledatatype;

/**
 * Java基本数据类型和byte数组相互转化 
 * @author liuyazhuang 
 * 
 */  
public class ByteTest {

    public static void main(String[] args) {
//        test2();

//        test3();

        test5ForFormat();
    }

    private static void test2() {
        String huanLine = "abc\nt\nx\n";
        System.out.print(huanLine);

        System.out.println(huanLine.length());

        byte[] huanLineToBytes = huanLine.getBytes();

        System.out.println(huanLineToBytes.length);
        for (int i =0 ; i< huanLineToBytes.length ; i++){
            System.out.println(huanLineToBytes[i]);
            if (huanLineToBytes[i] == "\n".getBytes()[0]){
                System.out.println("发现换行符 index：" + i);
            }

        }

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
    private static void test3() {

        System.out.println("" + Integer.toBinaryString(16));
        System.out.println("" + Integer.toHexString(16));
        System.out.println("" + Integer.toOctalString(16));


    }
    private static void test4() {

        System.out.println("Byte.MAX_VALUE:" + Byte.MAX_VALUE);


    }
    private static void test5ForFormat() {
        byte[] outB = new byte[]{15,1};
        String format = String.format("0x%02x", outB[0]);
        String format2 = String.format("0x%02x", outB[1]);
        System.out.println("format:" + format);
        System.out.println("format2:" + format2);


    }
} 