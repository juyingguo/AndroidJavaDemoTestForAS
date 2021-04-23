package com.file;

import com.utils.FileUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * Date:2021/4/12,15:19
 * author:jy
 */
public class FileRenameTest {
    public static void main(String[] args) {
        renameFile();        
    }
    private static String ROOT_DIR = "D:\\juying\\as1_5project\\ibotnsvn\\ibotncourse\\IbotnIzlCourseNT_as\\izlcoursezbnt\\src\\main\\assets\\web\\IbotnCourse_zb\\view\\2B_10_FindFriends";
    private static void renameFile() {
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
                            System.out.println("renameFile(),find file [ file name start with test_ ],rename to no test_ prefix. old name:" + name);
                            file.renameTo(new File(file.getParentFile(),newName));
//                            System.out.println("renameFile(),file new name file.getName():" + file.getName());

                        }

                    }
                }
            }
        }).start();
    }
}
