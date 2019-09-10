package com.utils;

/**
 * Date:2019/9/9,14:23
 * author:jy
 */
public class BinaryUtils {
    public static String toBinaryString(int num) {
        if (num == 0) return ""+0;
        String result = "";
        // 左面0的个数
        int n = Integer.numberOfLeadingZeros(num);
        System.out.println("num <<= n"+(num <<= n));
        for (int i=0; i<32-n; ++i) {
            int x = (Integer.numberOfLeadingZeros(num) == 0)?1:0;
            result += x;
            num <<= 1;
        }
        return result;
    }
/*
————————————————
    版权声明：本文为CSDN博主「_达闻西」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/hr_tao/article/details/73823421*/
}
