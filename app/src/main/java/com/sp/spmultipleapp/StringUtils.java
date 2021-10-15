package com.sp.spmultipleapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    /**
     * 第一字符串中的所有字符是包含在第二个字符中
     */
    public static boolean containsAllChars(String first,String second){

        if (first == null || second == null){
            return false;
        }
        char[] chars = first.toCharArray();
        for (char c:
             chars) {
            if (!second.contains(c + "")){
                return false;
            }
        }
        return true;
    }
    /**
     * 是否包含中文
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}