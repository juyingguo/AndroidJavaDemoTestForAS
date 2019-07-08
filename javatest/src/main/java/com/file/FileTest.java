package com.file;

import com.utils.IOManagerUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTest {
    private final static  String TAG = FileTest.class.getSimpleName();
    public static void main(String[] args) {
        test2();


    }

    private static void test2() {
        File file = new File("D:\\juying\\test-file","1.wav");
        System.out.println(TAG + ">>file.exists():" + file.exists());
        System.out.println(TAG + ">>file.isFile():" + file.isFile());
        System.out.println(TAG + ">>file.getAbsolutePath():" + file.getAbsolutePath());

        File fileMake = new File("D:\\juying\\test-file","test.txt");
        if (!fileMake.exists()){
            try {
                fileMake.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println(TAG + ">>fileMake.getAbsolutePath():" + fileMake.getAbsolutePath());

        try {
            FileInputStream fileInputStream = new FileInputStream(fileMake);
            fileInputStream.read();

            IOManagerUtils.closeSilently(fileInputStream);

            System.out.println(TAG + ">>fileInputStream:" + fileInputStream);

            IOManagerUtils.close(fileInputStream);

            System.out.println(TAG + ">>fileInputStream:" + fileInputStream);
            if (fileInputStream != null){
                System.out.println(TAG + ">>fileInputStream.read():" + fileInputStream.read());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
