package com.simpledatatype;

/**
 * Java基本数据类型和byte数组相互转化 
 * @author liuyazhuang 
 * 
 */  
public class ByteTest {

    public static void main(String[] args) {
//        test2();

        test3();
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
} 