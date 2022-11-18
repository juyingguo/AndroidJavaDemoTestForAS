package com.sp.spmultipleapp;

import android.util.Log;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Date:2022/3/25,15:56
 * author:jy <br/>
 * test for {@link com.utils.VideoEncryptUtils}
 */
public class VideoEncryptUtilsTest {

    /**
     * 结论从randomAccessFile.seek后指针下一个位置开始写，已经有字节就直接替换
     */
    @Test
    public void writeFileWithRandomAccessFileTest(){
        RandomAccessFile randomAccessFile =null;
        try {
            File file = new File("D:\\juying\\test-file\\test.txt");
            randomAccessFile = new RandomAccessFile(file,"rw");
            System.out.println("randomAccessFile.length():" + randomAccessFile.length());
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write("bb".getBytes("UTF-8"));
            System.out.println("write later,randomAccessFile.length():" + randomAccessFile.length());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile !=null){
                try {
                    randomAccessFile.close();
                    randomAccessFile=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
