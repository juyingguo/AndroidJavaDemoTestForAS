package com.sp.spmultipleapp;

import android.os.StatFs;
import android.util.Log;

import java.io.File;

public class FileTest {
    static  String TAG = FileTest.class.getSimpleName();
    public static void testFile(){

        File file = new File("/storage/uhost/");
        Log.d(TAG,"testFile>>file.exists():" + file.exists());
        Log.d(TAG,"testFile>>file.isDirectory():" + file.isDirectory());
        Log.d(TAG,"testFile>>file.isFile()" + file.isFile());
        StatFs statFs = new StatFs("/storage/uhost/");
        long size = statFs.getBlockCount() * statFs.getBlockSize();
        Log.d(TAG,"testFile>>uhost>>size：" + size
                + "\n statFs.getBlockCount():" + statFs.getBlockCount()
                + "\n statFs.getBlockSize():" + statFs.getBlockSize()
                + "\n statFs.getFreeBlocks():" + statFs.getFreeBlocks()
                + "\n statFs.getAvailableBlocks():" + statFs.getAvailableBlocks()
        );

        statFs = new StatFs("/storage/sd-ext/");
        long sdExtTotalSize = statFs.getTotalBytes() /1024 /1024;
        long sdExtTotalSize2 = statFs.getBlockCount() * statFs.getBlockSize() /1024 /1024;
        long sdExtTotalSize3 = statFs.getBlockCount() /1024 * statFs.getBlockSize() /1024;
        long sdExtTotalSize4 = statFs.getBlockCountLong() * statFs.getBlockSizeLong() /1024 /1024;
        Log.d(TAG,"testFile>>sd-ext>>sdExtTotalSize：" + sdExtTotalSize
                + "\n sdExtTotalSize2:" + sdExtTotalSize2
                + "\n sdExtTotalSize3:" + sdExtTotalSize3
                + "\n sdExtTotalSize4:" + sdExtTotalSize4
                + "\n getBlockCount:" + statFs.getBlockCount()
                + "\n getBlockCountLong:" + statFs.getBlockCountLong()
                + "\n getBlockSize:" + statFs.getBlockSize()
                + "\n getBlockSizeLong:" + statFs.getBlockSizeLong()
                + "\n Integer.MAX_VALUE:" + Integer.MAX_VALUE
                + "\n statFs.getBlockCount() * statFs.getBlockSize():" + statFs.getBlockCount() * statFs.getBlockSize()
                + "\n statFs.getBlockCountLong() * statFs.getBlockSizeLong():" + statFs.getBlockCountLong() * statFs.getBlockSizeLong()
        );

        long sdExtAvailableSize = statFs.getAvailableBlocks() * statFs.getBlockSize();
        long sdExtFreeSize = statFs.getFreeBlocks() * statFs.getBlockSize() / 1024 /1024;
        Log.d(TAG,"testFile>>sd-ext>>sdExtAvailableSize：" + sdExtAvailableSize
                        + "\n sdExtFreeSize: " + sdExtFreeSize
                        + "\n statFs.getBlockCount(): " + statFs.getBlockCount()
                        + "\n statFs.getAvailableBlocks(): " + statFs.getAvailableBlocks()
                        + "\n statFs.getFreeBlocks(): " + statFs.getFreeBlocks()
        );

        StringBuilder stringBuilder = new StringBuilder();

        Log.d(TAG,"testFile>>stringBuilder.toString()" + stringBuilder.toString());

    }
}
