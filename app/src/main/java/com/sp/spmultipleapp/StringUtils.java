package com.sp.spmultipleapp;

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
}