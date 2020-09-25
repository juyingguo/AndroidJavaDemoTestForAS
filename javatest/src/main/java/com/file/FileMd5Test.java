package com.file;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileMd5Test {

    /**
     * JAVA中获取文件MD5值的四种方法
     *https://blog.csdn.net/weixin_40129263/article/details/82837900
     *
     *
     */
    private final static  String TAG = FileMd5Test.class.getSimpleName();
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();

    }


    private static void test1() {
        //原本为：md5='04a986431446e82fcbd26dffbeb03fac'
        File file = new File("D:\\juying\\test-file\\verifyFileMd5","18-变整齐的玩具箱.jpg");
        System.out.println(TAG + " test1 >>file.exists():" + file.exists());
        if (file.isFile()){
            String fileMD5 = getMD5One(file.getAbsolutePath());
            System.out.println(TAG + " test1 >>fileMD5:" + fileMD5);
            //该方式计算后的md5为::4a986431446e82fcbd26dffbeb03fac
        }

    }

    private static void test2() {
        //原本为：md5='04a986431446e82fcbd26dffbeb03fac'
        File file = new File("D:\\juying\\test-file\\verifyFileMd5","18-变整齐的玩具箱.jpg");
        System.out.println(TAG + " test2 >>file.exists():" + file.exists());
        if (file.isFile()){
            String fileMD5 = getFileMD5(file);
            System.out.println(TAG + " test2 >>fileMD5:" + fileMD5);
            //该方式计算后的md5为::4a986431446e82fcbd26dffbeb03fac,错误，前面少一个0
        }

    }

    private static void test3() {
        //原本为：md5='04a986431446e82fcbd26dffbeb03fac'
        File file = new File("D:\\juying\\test-file\\verifyFileMd5","18-变整齐的玩具箱.jpg");
        System.out.println(TAG + " test3>>file.exists():" + file.exists());
        if (file.isFile()){
            String fileMD5 = getMD5Three(file.getAbsolutePath());
            System.out.println(TAG + " test3>>fileMD5:" + fileMD5);
            //该方式计算后的md5为::4a986431446e82fcbd26dffbeb03fac
        }
    }
    private static void test4() {
        //原本为：md5='04a986431446e82fcbd26dffbeb03fac'

        File file = new File("D:\\juying\\test-file\\verifyFileMd5","18-变整齐的玩具箱.jpg");
        System.out.println(TAG + " test4>>file.exists():" + file.exists());
        if (file.isFile()){
            String fileMD5 = null;
            try {
                fileMD5 = DigestUtils.md5Hex(new FileInputStream(file.getAbsoluteFile()));
                System.out.println(TAG + " test4>>fileMD5:" + fileMD5);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //该方式计算后的md5为test4>>fileMD5:04a986431446e82fcbd26dffbeb03fac   正确。
        }
    }

    private static void test5() {
        //原本为：md5='04a986431446e82fcbd26dffbeb03fac'

        File file = new File("D:\\juying\\test-file\\verifyFileMd5","18-变整齐的玩具箱.jpg");
        System.out.println(TAG + " test5>>file.exists():" + file.exists());
        if (file.isFile()){
            String fileMD5 = null;
            fileMD5 = getFileMD5Five(file);
            System.out.println(TAG + " test5>>fileMD5:" + fileMD5);
            //该方式计算后的md5为
        }
    }

    /**
     * 对于实际文件md5前面是零的；该方式前面少一个0，错误方式。
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
    private final static String[] strHex = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String getMD5One(String path) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(FileUtils.readFileToByteArray(new File(path)));
            for (int i = 0; i < b.length; i++) {
                int d = b[i];
                if (d < 0) {
                    d += 256;
                }
                int d1 = d / 16;
                int d2 = d % 16;
                sb.append(strHex[d1] + strHex[d2]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    public static String getMD5Three(String path) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi.toString(16);
    }


    public static String getFileMD5Five(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bytesToHexString(digest.digest());
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
