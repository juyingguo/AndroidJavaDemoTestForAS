package com.exception;

import com.file.FileTest;
import com.utils.IOManagerUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExceptionTestTry02 {
    private final static  String TAG = ExceptionTestTry02.class.getSimpleName();
    public static void main(String[] args) {
//        test2();


        testTry();

    }

    private static void testTry() {

        try {
            int a = 10 * 10;
            System.out.println(TAG + ">>a:" + a);
            int b = a /0;
            System.out.println(TAG + ">>b:" + b);

        }catch (Exception e){
            System.out.println(TAG + ">>e:" + e.getMessage());
        }
        finally {
            System.out.println(TAG + ">>testTryFinally end");
        }
        System.out.println(TAG + ">>testTry end");
    }


}
