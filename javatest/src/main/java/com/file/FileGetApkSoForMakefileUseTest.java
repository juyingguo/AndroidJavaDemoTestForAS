package com.file;

import com.utils.FileUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * Date:2021/4/12,15:19
 * author:jy
 * <p>获取apk中的so，便于在Makefile中使用.<br/>
 *  形式如下：
 *  LOCAL_PREBUILT_JNI_LIBS += lib/libcmplayer_v5_8.so
 *  ...
 * </p>
 */
public class FileGetApkSoForMakefileUseTest {
    public static void main(String[] args) {
        getSoList();
    }
    private static String ROOT_DIR = "D:\\juying\\work-test\\apk\\related_apk\\IQYSpeaker_landscape_kedaxunfei_12.7.0_81590_20210804\\lib\\armeabi-v7a";
    private static String SO_IN_MAKEFILE_PREFIX = "LOCAL_PREBUILT_JNI_LIBS += lib/";
    private static void getSoList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (FileUtils.isDir(ROOT_DIR)){
                    List<File> files = FileUtils.listFilesInDirWithFilter(ROOT_DIR, new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return true;
                        }
                    });
                    StringBuilder sb = new StringBuilder();
                    for (File file:files) {
                        String name = FileUtils.getFileName(file);
                        sb.append(SO_IN_MAKEFILE_PREFIX + name + "\n");
                    }
                    System.out.println("so list : \n " + sb.toString());
                }
            }
        }).start();
    }
}
