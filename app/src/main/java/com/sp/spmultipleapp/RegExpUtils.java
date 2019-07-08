package com.sp.spmultipleapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jy on 2017/12/6.
 */

public class RegExpUtils {
    /**
     * 清除掉所有特殊字符
     * @param str
     * @return
     */
    public static String filterSpecicalChars(String str){
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    /**
     * 匹配所有到的数字，包含负数和小数
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        String regEx="-?[0-9]+.?[0-9]+";
        boolean matches = Pattern.matches(regEx, str);
        return matches;
    }
    /**
     * 过滤空格
     * @param str
     * @return
     */
    public static String filterSpace(String str) {
        String regEx="[ ]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    /**
     * 过滤开头的数字
     * @param str
     * @return
     */
    public static String filterStartNumber(String str) {
        String regEx="^[0-9]*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
