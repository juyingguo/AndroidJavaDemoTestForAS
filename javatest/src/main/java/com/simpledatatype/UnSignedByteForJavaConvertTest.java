package com.simpledatatype;

/**
 * Date:2020/4/8,16:12
 * author:jy.<br/>
 * java 无符号byte转换
 * https://www.cnblogs.com/king1302217/p/6134370.html
 *
 * java中的byte类型是有符号的，值得范围是-128-127
 *
 * 做网络通讯时，接收过来的数据往往都是无符号的byte，值得范围是0-255
 *
 * 因此直接转换时，存储到java显示的值就会有问题
 */
public class UnSignedByteForJavaConvertTest {
    public static void main(String[] args) {
        test();

    }

    private static void test() {
        int ori=200;
        System.out.println("原始byte值："+ori);
        Byte b=(byte)ori;
        System.out.println("java中byte值："+b);
        Integer i=b.intValue();
        System.out.println("转换后的int值："+i);
        System.out.println("存储的2进制值："+Integer.toBinaryString(i));
        Integer i_trans=i&0xFF;
        System.out.println("&0xFF后的："+i_trans);
        System.out.println("(byte)0XF1):"+((byte)0XF1));
        int test = ((byte)0xF1) & 0xFF;
        System.out.println("test:"+test);
        System.out.println("(1>>>4):"+(1>>>4));
        System.out.println("(1>>4):"+(1>>4));
    }
}
