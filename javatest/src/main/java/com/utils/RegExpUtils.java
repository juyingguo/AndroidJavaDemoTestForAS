package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by jy on 2017/12/6.
 */
public class RegExpUtils {
    /**
     * 自定义配置正则表达式。请勿修改内容。
     */
    public final static String CUSTOM_REGEX_1 =  "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。.，、？-]";
    public final static String CUSTOM_REGEX_2 =  "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。.，、？-]";
    /**
     * 清除掉所有特殊字符
     * @param str String
     * @return String
     * @throws PatternSyntaxException PatternSyntaxException
     */
    public static String filterSpecicalChars(String str) throws PatternSyntaxException {
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    /**
     * 根据指定的正则表达式，将内容替换为空字符
     * @param  regex
     *         The expression to be compiled
     * @param str String
     * @return String
     * @throws PatternSyntaxException PatternSyntaxException
     */
    public static String replaceToSpace(String regex,String str) throws PatternSyntaxException {
        String regEx= regex;
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
     * @return 过滤开头的数字,同时再去掉开头和结尾的空格
     */
    public static String filterStartNumber(String str) {
        String regEx="^[0-9]*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}