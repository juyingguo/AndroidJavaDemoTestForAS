package com.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UTF8Util {
    private static final String TAG = UTF8Util.class.getSimpleName();

    /**
     * 使用utf-8编码
     * @param str
     * @return
     */
    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println(TAG+">>encode catch exception :" + str);
            return str;
        }
    }

    /**
     * 使用utf-8解码
     * @param str
     * @return
     */
    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println(TAG+">>decode catch exception :" + str);
            return str;
        }
    }
}
