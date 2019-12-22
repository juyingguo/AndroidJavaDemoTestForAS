package com.exception;

import com.file.FileTest;
import com.utils.IOManagerUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AssignmentExceptionTest01 {
    private final static  String TAG = FileTest.class.getSimpleName();
    public static void main(String[] args) {



        String result = "adc";
        try {
            result = calResult02();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(TAG + " result:" + result);


    }


    private static String calResult() throws Exception {
        String result = null;

        if (2/0 == 2){
            result = "error";
        }else {
            result = "ok";
        }
        return result;

    }
    private static String calResult02() {
        String result = null;

        if (2/0 == 2){
            result = "error";
            throw  new RuntimeException();
        }else {
            result = "ok";
        }
        return result;

    }

}
