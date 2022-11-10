package com.file;

import com.utils.FileUtils;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * Date:2022/8/18,17:53
 * author:jy
 */
public class FileRenameAndCopyTest {
    public static void main(String[] args) {
//        renameWithTrimPrefixFile();
        renameWithReplaceNameFile();
//        deleteFile();
    }
    private static String ROOT_SRC_DIR = "D:\\用户目录\\下载\\消息-分组 8";
    private static String ROOT_DEST_DIR = "D:\\juying\\as1_5project\\ibotnsvn\\IbotnNewApp_family\\app\\src\\main\\res";
    private final static String[] COPY_UNDER_DIR = {"drawable-hdpi","drawable-mdpi","drawable-xhdpi","drawable-xxhdpi","drawable-xxxhdpi"};
    private static String NEW_FILE_NAME_PREFIX = "ic_msg_track";
    //    private static String ROOT_SRC_DIR = "D:\\juying\\as1_5project\\ibotnsvn\\ibotncourse\\IbotnIzlCourseNT_as\\izlcoursezbnt\\src\\main\\assets\\web\\IbotnCourse_zb\\assets\\images";

    /**
     * 重命名，修改文件名部分前缀。
     */
    private static void renameWithTrimPrefixFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (FileUtils.isDir(ROOT_SRC_DIR)){
                    List<File> files = FileUtils.listFilesInDirWithFilter(ROOT_SRC_DIR, new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            if (FileUtils.isImage(new File(dir, name).getAbsolutePath())
                                    || FileUtils.isAudio(new File(dir, name).getAbsolutePath())) {
                                return true;
                            }
                            return false;
                        }
                    });
                    for (File file:files) {
                        String name = FileUtils.getFileName(file);
                        if (name.startsWith("test_")){
                            String newName = name.substring("test_".length());
                            System.out.println("renameWithTrimPrefixFile(),find file [ file name start with test_ ],rename to no test_ prefix,old name:" + name);
                            file.renameTo(new File(file.getParentFile(),newName));
//                            System.out.println("renameWithTrimPrefixFile(),file new name file.getName():" + file.getName());

                        }

                    }
                }
            }
        }).start();
    }

    /**
     * 完全重命名文件名
     */
    private static void renameWithReplaceNameFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (FileUtils.isDir(ROOT_SRC_DIR)){
                    List<File> files = FileUtils.listFilesInDirWithFilter(ROOT_SRC_DIR, new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            if (FileUtils.isImage(new File(dir, name).getAbsolutePath())
                                    || FileUtils.isAudio(new File(dir, name).getAbsolutePath())) {
                                return true;
                            }
                            return false;
                        }
                    });
                    for (File file:files) {
                        String name = FileUtils.getFileName(file);
                        if(!name.startsWith(NEW_FILE_NAME_PREFIX)){
                            String newName = NEW_FILE_NAME_PREFIX + name.substring(name.lastIndexOf("."));
                            file.renameTo(new File(file.getParentFile(),newName));
                        }
                    }
                }
                System.out.println("finish.");
                System.out.println("copy files start.");
                copyFile();
                System.out.println("copy files finish.");
            }
        }).start();
    }

    private static void copyFile() {
        if (FileUtils.isDir(ROOT_SRC_DIR)){
            List<File> files = FileUtils.listFilesInDirWithFilter(ROOT_SRC_DIR, new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (FileUtils.isImage(new File(dir, name).getAbsolutePath())
                            || FileUtils.isAudio(new File(dir, name).getAbsolutePath())) {
                        return true;
                    }
                    return false;
                }
            });
            File newDestFile = null;
            for (File file:files) {
                String currentFileName = FileUtils.getFileName(file);
                File parentFile = file.getParentFile();
                String parentName = parentFile.getName();
                for (String currentName : COPY_UNDER_DIR) {
                    if (currentName.equals(parentName)){
                        newDestFile = new File(ROOT_DEST_DIR + File.separator + currentName + File.separator + currentFileName);
                        FileUtils.copyFile(file,newDestFile);
                    }
                }
            }
        }
    }
    private static String DELETE_FILE_NAME = "icon_login_bg.png";

//    @Test
    private static void deleteFile(){
        System.out.println("delete files start.");
        if (FileUtils.isDir(ROOT_DEST_DIR)){
            List<File> files = FileUtils.listFilesInDirWithFilter(ROOT_DEST_DIR, new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (FileUtils.isImage(new File(dir, name).getAbsolutePath())
                            && name.equals(DELETE_FILE_NAME)) {
                        return true;
                    }
                    return false;
                }
            });
            File newDestFile = null;
            for (File file:files) {
                File parentFile = file.getParentFile();
                String parentName = parentFile.getName();
                for (String currentName : COPY_UNDER_DIR) {
                    if (currentName.equals(parentName)){
                        FileUtils.deleteFile(file);
                    }
                }
            }
        }
        System.out.println("delete files finish.");
    }
}
