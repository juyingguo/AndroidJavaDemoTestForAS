package com.algorithm.hashtest;

/**
 * Date:2019/8/13,13:49
 * author:jy
 */
public class AdditiveHashTest {
    public static void main(String[] args) {
        test();

    }

    private static void test() {

        int hash = additiveHash("ab", 7);
        System.out.println("hash:"  + hash);
        System.out.println("hash02:"  + additiveHash("abc", 7));
        System.out.println("hash03:"  + additiveHash("def", 7));
        System.out.println("hash03:"  + additiveHash("ghi", 7));
    }
    static int additiveHash(String key, int prime){

        int hash, i;

        for (hash = key.length(), i = 0; i <key.length(); i++)

        hash += key.charAt(i);
        return (hash % prime);

    }
/*---------------------
    版权声明：本文为CSDN博主「Beyond_2016」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/Beyond_2016/article/details/81286360*/
}
