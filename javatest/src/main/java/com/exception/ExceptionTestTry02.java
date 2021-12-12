package com.exception;

import com.file.FileTest;
import com.utils.IOManagerUtils;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExceptionTestTry02 {
    private final static  String TAG = ExceptionTestTry02.class.getSimpleName();
//    public static void main(String[] args) {
////        test2();
//
//
//        testTry();
//
//    }
    @Test
    public void testReturnTry() {

        try {
            int a = 10 * 10;
            System.out.println(TAG + ">>a:" + a);
//            int b = a /0;
//            System.out.println(TAG + ">>b:" + b);
            return;
        }catch (Exception e){
            System.out.println(TAG + ">>testReturnTry catch e:" + e.getMessage());
        }finally {
            System.out.println(TAG + ">>testReturnTry finally end");
        }
        System.out.println(TAG + ">>testReturnTry end");
    }
    @Test
    public void testContinueInTryFinally() {
        int a = 10;
        System.out.println(TAG + ">>a:" + a);
        int i = 0;
        while (i<a){
            try {
                i++;
                if (i <8 ) continue;
                System.out.println(TAG + ">>i:" + i);
            }catch (Exception e){
                System.out.println(TAG + ">>testContinueInTryFinally catch e:" + e.getMessage());
            }finally {
                System.out.println(TAG + ">>testContinueInTryFinally finally end");
            }
        }

        System.out.println(TAG + ">>testContinueInTryFinally end");
    }

}
