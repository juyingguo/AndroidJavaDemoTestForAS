package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * 1、多音字拦截。处理与语音tts相关的指定规则的多音字问题。<br/>
     * 2、举例：解[=jie4]甲归田，过滤后为：解甲归田 <br/>
     * @param str 字符串
     * @return 如果包含就过滤掉，不包含就不做任何处理。
     */
    public static String polyphonicInterception(String str){
        if (str == null){
            return str;
        }
        ////解[=jie4]甲归田。。
        String result = str;
        int indexStart = result.indexOf("[=");
        int indexEnd = result.indexOf("]");
        if (indexStart >=0 && indexEnd > indexStart){
            final String dealedStr = result.substring(0,indexStart) + result.substring(indexEnd + 1);
            result = dealedStr;
            //如果截取后的字符串，还包含多音字，就递归过滤
            if (dealedStr != null && dealedStr.indexOf("[=") >=0 && dealedStr.indexOf("]") > dealedStr.indexOf("[=")){
                return polyphonicInterception(dealedStr);
            }

        }

        return result;
    }
    public static boolean isSpace(String str) {

        return null == str || str.trim().equalsIgnoreCase("");
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
