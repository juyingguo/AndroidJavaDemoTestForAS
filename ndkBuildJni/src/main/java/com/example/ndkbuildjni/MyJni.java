package com.example.ndkbuildjni;

/**
 * Date:2021/1/18,15:59
 * author:jy
 */
public class MyJni {
    static {
        System.loadLibrary("MyJni");
    }
    public static native String get();
}
