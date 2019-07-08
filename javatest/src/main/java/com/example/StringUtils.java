package com.example;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author other
 * @date 创建时间：2016年12月23日 下午3:34:56
 *  字符串工具类
 *  modify:jy
 */
public class StringUtils {
    /**
     * 提取出城市或者县
     *
     * @param city
     * @param district
     * @return
     */
    public static String extractLocation(final String city, final String district) {
        return district.contains("县") ? district.substring(0, district.length() - 1) : city.substring(0, city.length() - 1);
    }

    /**
     * 判断字符串是否为电话号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
//        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Pattern p = Pattern.compile("^(1)\\d{10}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    /**
     * @author jy
     * @param charsetName
     * @return true 为空
     * 字符串是否为空
     */
    public static boolean isSpace(String charsetName) {
        if (charsetName == null || charsetName.trim().equals("")){
            return true;
        }
        return false;
    }

}