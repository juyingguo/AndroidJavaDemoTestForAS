package com.simpledatatype;

/**
 * Date:2019/7/31,13:55
 * author:jy
 */
public class IntegerAndSimpleIntDistinguishTest {
    public static void main(String[] args) {
        test2();
        test3();
        test4();
    }

    private static void test2() {

        Integer i = new Integer(100);
        Integer j = new Integer(100);
        System.out.println(i == j); //false
        System.out.println(i.equals(j)); //true
        System.out.println(i.equals(100)); //true
    }
    /**
     * 非new生成的Integer变量和new Integer()生成的变量比较时，结果为false。
     * （因为非new生成的Integer变量指向的是java常量池中的对象，而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同）
     */
    private static void test3() {
        Integer i = new Integer(100);
        Integer j = 100;
        System.out.println(i == j); //false
    }
    /**
     * 对于两个非new生成的Integer对象，进行比较时，如果两个变量的值在区间-128到127之间，则比较结果为true，如果两个变量的值不在此区间，则比较结果为false
     */
    private static void test4() {
        /**
         * java在编译Integer i = 100 ;时，会翻译成为Integer i = Integer.valueOf(100)；，而java API中对Integer类型的valueOf的定义如下：

         public static Integer valueOf(int i){
         assert IntegerCache.high >= 127;
         if (i >= IntegerCache.low && i <= IntegerCache.high){
         return IntegerCache.cache[i + (-IntegerCache.low)];
         }
         return new Integer(i);
         }
         作者：bailaoshi666
         来源：CSDN
         原文：https://blog.csdn.net/bailaoshi666/article/details/82721455
         版权声明：本文为博主原创文章，转载请附上博文链接！
         */
        Integer i = 100;
        Integer j = 100;
        System.out.println(i == j); //true
        Integer i1 = 128;
        Integer j1 = 128;
        System.out.println(i1 == j1); //false

        Integer i2 = Integer.valueOf(128);
        Integer j2 = Integer.valueOf(128);
        System.out.println(i2 == j2); //false
    }
}
