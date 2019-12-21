package com.utils;

import java.util.UUID;

/**
 * Date:2019/12/17,17:59
 * author:jy
 */
public class UuidUtil {
    /*
    利用Java生成UUID
    https://www.cnblogs.com/duzhentong/p/7816539.html
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            //注意replaceAll前面的是正则表达式
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
        testUuid02();
    }

    private static void testUuid02() {
        for (int i = 0; i < 5; i++) {
            //注意replaceAll前面的是正则表达式
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            System.out.println(uuid);
        }
    }
}
