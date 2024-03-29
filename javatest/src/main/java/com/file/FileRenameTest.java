package com.file;

import com.utils.FileUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * Date:2021/4/12,15:19
 * author:jy
 * <p>file name rename tool</p>
 * <p>for example case: if file name start with "test_" ,trim it and remain the later string</p>
 */
public class FileRenameTest {
    public static void main(String[] args) {
//        renameWithTrimPrefixFile();
        renameWithReplaceNameFile();
    }
    private static String ROOT_DIR = "D:\\juying\\work-test\\game-course\\html5\\izhile_xiaxueqi\\temp";
//    private static String ROOT_DIR = "D:\\juying\\as1_5project\\ibotnsvn\\ibotncourse\\IbotnIzlCourseNT_as\\izlcoursezbnt\\src\\main\\assets\\web\\IbotnCourse_zb\\assets\\images";
    private static void renameWithTrimPrefixFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (FileUtils.isDir(ROOT_DIR)){
                    List<File> files = FileUtils.listFilesInDirWithFilter(ROOT_DIR, new FilenameFilter() {
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
    private static void renameWithReplaceNameFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (FileUtils.isDir(ROOT_DIR)){
                    List<File> files = FileUtils.listFilesInDirWithFilter(ROOT_DIR, new FilenameFilter() {
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
                        if (name.startsWith("second_")){
                            String newName = "eighth_" + name.substring("second_".length());
                            System.out.println("renameWithReplaceNameFile(),find file [ file name start with test_ ],rename to no test_ prefix,old name:" + name);
                            file.renameTo(new File(file.getParentFile(),newName));
//                            System.out.println("renameWithTrimPrefixFile(),file new name file.getName():" + file.getName());

                        }

                    }
                }
            }
        }).start();
    }
}
