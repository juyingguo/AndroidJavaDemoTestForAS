package com.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;


import com.sp.spmultipleapp.application.CoreApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PictureUtil {

//    private static String tempRootDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ImgTmp";
    public static String tempRootDir = CoreApplication.getInstance().getFilesDir() + "/ImgTmp";
    private static final String TAG = PictureUtil.class.getSimpleName();

    //图片压缩大小控制，单位kb
    private static final int IMAGE_SIZE = 100;

    //图片质量比例
    private static final int IMAGE_COMPRESSION = 50;
    /**
     * kb
     */
    public static final float MIN_COMPRESS_PICTURE_FILE_SIZE = (float) (1*1024F);

    /**
     * 1.质量压缩
     *
     * @param image
     * @return
     */
    private static Bitmap compressImage(Bitmap image) {
        LogUtil.e(TAG,"yison compressImage width = "+image.getWidth() + " height = "+image.getHeight());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 50;
        int length = baos.toByteArray().length / 1024;
        LogUtil.e(TAG,"yison compressImage length 111 "+length);
        while ( length > IMAGE_SIZE && options > IMAGE_COMPRESSION) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            length = baos.toByteArray().length / 1024;
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中


        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片

        LogUtil.e(TAG,"yison compressImage width = "+bitmap.getWidth() + " height = "+bitmap.getHeight() + " options = "+options );

        return bitmap;
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param srcPath 图片路径
     * @return
     */
    private static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;

        LogUtil.e(TAG,"yison compressImage outWidth = "+w + " outHeight = "+h);

        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        //进行 质量压缩
        //Bitmap bitmap = BitmapFactory.decodeFile(srcPath);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    public static String CompressImage(String srcPath)throws IOException{

        Bitmap bm = BitmapFactory.decodeFile(srcPath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();//100kb
        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos);//40kb
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        //Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片

        //得到文件名
        String imgName = getFilepath(srcPath);
        //得到存放路径
        String sdPath = tempRootDir;
        //获取 sdcard的跟目录

        File parent = new File(sdPath);
        if (!parent.exists()) {
            //创建路径
            parent.mkdirs();
        }
        //写入 临时文件
        File file = new File(parent, imgName);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();
        baos.close();
        //返回图片路径
        LogUtil.e(TAG,"yison compressImage bitmapToPath = "+sdPath + "/" + imgName);
        return sdPath + "/" + imgName;
    }

    /**
     * 得到临时图片路径
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String bitmapToPath(String filePath) throws IOException {

        Bitmap bm = getimage(filePath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();//100kb
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);//40kb
        LogUtil.e(TAG,"yison compressImage bitmapToPath width = "+bm.getWidth() + " height = "+bm.getHeight() + " length = "+baos.toByteArray().length);

        //得到文件名
        String imgName = getFilepath(filePath);
        //得到存放路径
        String sdPath = tempRootDir;
        //获取 sdcard的跟目录

        File parent = new File(sdPath);
        if (!parent.exists()) {
            //创建路径
            parent.mkdirs();
        }
        //写入 临时文件
        File file = new File(parent, imgName);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();
        baos.close();
        //返回图片路径
        LogUtil.e(TAG,"yison compressImage bitmapToPath = "+sdPath + "/" + imgName);
        return sdPath + "/" + imgName;

    }

    /**
     * 得到临时图片路径
     *
     * @param filePath String
     * @param quality  Hint to the compressor, 0-100
     * @return bitmapToPath
     * @throws IOException
     */
    public static String bitmapToPath(String filePath, int quality) throws IOException {

        Bitmap bm = getimage(filePath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        LogUtil.e(TAG,"yison compressImage bitmapToPath width = "+bm.getWidth() + " height = "+bm.getHeight() + " length = "+baos.toByteArray().length);

        //得到文件名
        String imgName = getFilepath(filePath);
        //得到存放路径
        String sdPath = tempRootDir;
        //获取 sdcard的跟目录

        File parent = new File(sdPath);
        if (!parent.exists()) {
            //创建路径
            parent.mkdirs();
        }
        //写入 临时文件
        File file = new File(parent, imgName);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();
        baos.close();
        //返回图片路径
        return sdPath + "/" + imgName;
    }
    /**
     * 得到临时图片路径
     *
     * @param filePath String
     * @param quality  Hint to the compressor, 0-100
     * @return bitmapToPath
     * @throws IOException IOException
     */
    public static String pictureCompressToFilepath(String filePath, int quality) throws IOException {

        Bitmap bm =  BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.JPEG, quality, baos);// TODO: 2019/10/18 格式对实际图片的影响
        LogUtil.e(TAG,"rawBitmapToFilepath compressImage bitmapToPath width = "+bm.getWidth() + " height = "+bm.getHeight() + " length = "+baos.toByteArray().length);

        String imgName = getFilepath(filePath);
        //得到存放路径
        String sdPath = tempRootDir;
        //获取 sdcard的跟目录
        File parent = new File(sdPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        File file = new File(parent, imgName);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();
        baos.close();
        //返回图片路径
        if (!bm.isRecycled()){
            bm.recycle();
        }
        return sdPath + File.separator + imgName;
    }
    /**
     * 得到临时图片路径
     *
     * @param filePath String
     * @param quality  Hint to the compressor, 0-100
     * @return bitmapToPath
     * @throws IOException IOException
     */
    public static String rawBitmapToFilepath(Bitmap bitmap,String filePath, int quality) throws IOException {
        LogUtil.e(TAG,"rawBitmapToFilepath,bitmap:"+ bitmap + ",filePath:" +filePath + ",quality:" + quality);
        if (bitmap == null){
            throw new IllegalArgumentException("bitmap can not be null");
        }
        if (!FileUtils.isFileExists(filePath)){
            throw new IllegalArgumentException("filePath not exists");
        }
        final String appendFileName = "_rote";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);// TODO: 2019/10/18 格式对实际图片的影响,JPEG, 使用PNG格式保存后的图片比较大
        LogUtil.e(TAG,"rawBitmapToFilepath compressImage bitmapToPath width = "+ bitmap.getWidth() + " height = "+ bitmap.getHeight() + " length = "+baos.toByteArray().length);

        String imgName = FileUtils.getFileNameNoExtension(filePath) + appendFileName + "."  + FileUtils.getFileExtension(filePath) ;
        LogUtil.e(TAG,"rawBitmapToFilepath ，imgName:"+ imgName);
        //根据源文件，获取父目录，新生成的文件也在放在同样目录下。
        File rawFile = new File(filePath);
        File baseDir = rawFile.getParentFile();
        if (!baseDir.exists()){
            baseDir.mkdirs();
        }
        String sdPath = baseDir.getPath();
        File parent = new File(sdPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        File newFile = new File(parent, imgName);
        newFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();
        baos.close();
        return sdPath + File.separator + imgName;//返回图片路径
    }
    /**
     * 尺寸及质量压缩
     *
     * @param filePath String
     * @param sampleSize int
     * @param quality  Hint to the compressor, 0-100
     * @return bitmapToPath
     * @throws IOException IOException
     */
    public static String pictureScaleAndQualityCompressToFilepath(String filePath, int sampleSize, int quality) throws IOException {
        LogUtil.e(TAG,"pictureScaleAndQualityCompressToFilepath,filePath:" +filePath + ",quality:" + quality);
        if (!FileUtils.isFileExists(filePath)){
            throw new IllegalArgumentException("filePath not exists");
        }
        final String appendFileName = "_deal";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath,options);
        LogUtil.e(TAG,"pictureScaleAndQualityCompressToFilepath  bitmap width = "+ bitmap.getWidth() + " height = "+ bitmap.getHeight());
        options.inSampleSize = sampleSize;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(filePath,options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);// TODO: 2019/10/18 格式对实际图片的影响,JPEG, 使用PNG格式保存后的图片比较大
        LogUtil.e(TAG,"pictureScaleAndQualityCompressToFilepath compressImage bitmapToPath width = "+ bitmap.getWidth() + " height = "+ bitmap.getHeight() + " length = "+baos.toByteArray().length/1024F);

        String imgName = FileUtils.getFileNameNoExtension(filePath) + appendFileName + "."  + FileUtils.getFileExtension(filePath) ;
        LogUtil.e(TAG,"pictureScaleAndQualityCompressToFilepath ，imgName:"+ imgName);
        //根据源文件，获取父目录，新生成的文件也在放在同样目录下。
        File rawFile = new File(filePath);
        File baseDir = rawFile.getParentFile();
        if (!baseDir.exists()){
            baseDir.mkdirs();
        }
        String sdPath = baseDir.getPath();
        File parent = new File(sdPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        File newFile = new File(parent, imgName);
        newFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();
        baos.close();
        if (!bitmap.isRecycled()){
            bitmap.recycle();
        }
        return sdPath + File.separator + imgName;//返回图片路径
    }
    /**
     * @param path
     * @return
     */
    private static String getFilepath(String path) {
        return System.currentTimeMillis() + getExtensionName(path);
    }


    /*
     * 获取文件扩展名
     */
    private static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot, filename.length());
            }
        }
        return filename;
    }


    /**
     * 删除临时文件
     *
     * @param imgs
     */
    public static void deleteImgTmp(List<String> imgs) {

        for (String string : imgs) {
            File file = new File(string);
            if (file.exists()) {
                file.delete();
            }
        }

    }

    /**
     * 只压缩上传的图片
     */
    public static Map<String, ArrayList<File>> compressFileMap(Map<String, ArrayList<File>> fileMap) {
        Map<String, ArrayList<File>> newFileMap = new HashMap<>();
        try {
            for (String key : fileMap.keySet()) {
                ArrayList<File> files = fileMap.get(key);
                ArrayList<File> newFiles = new ArrayList<>();
                for (File file : files) {
                    //2018/8/4 只压缩上传的图片
                    File newFile;
                    if (FileUtils.isImage(file.getPath())) {
                        String newFilePath = bitmapToPath(file.getPath());
                        newFile = new File(newFilePath);
                    } else {
                        newFile = file;
                    }
                    newFiles.add(newFile);
                }
                newFileMap.put(key, newFiles);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileMap;
    }

    /**
     * 只压缩上传的图片
     */
    public static List<String> compressFile(List<String> filePaths) {
                ArrayList<String> newFiles = new ArrayList<>();
                for (String filePath : filePaths) {
                    File file = new File(filePath);
                    //2018/8/4 只压缩上传的图片
                    String newFile;
                    if (FileUtils.isImage(file.getPath())) {
                        try {
                            newFile = bitmapToPath(file.getPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                            newFile = null;
                        }
                    } else {
                        newFile = filePath;
                    }
                    newFiles.add(newFile);
                }
        return newFiles;
    }

    /**
     * 只压缩上传的图片 ,可指定压缩质量
     *
     * @param fileMap Map<String, ArrayList<File>>
     * @param quality Hint to the compressor, 0-100
     * @return Map<String   ,       ArrayList   <   File>>
     */
    public static Map<String, ArrayList<File>> compressFileMap(Map<String, ArrayList<File>> fileMap, int quality) {
        Map<String, ArrayList<File>> newFileMap = new HashMap<>();
        try {
            for (String key : fileMap.keySet()) {
                ArrayList<File> files = fileMap.get(key);
                ArrayList<File> newFiles = new ArrayList<>();
                for (File file : files) {
                    //2018/8/4 只压缩上传的图片
                    File newFile;
                    if (FileUtils.isImage(file.getPath())) {
                        String newFilePath = bitmapToPath(file.getPath(), quality);
                        newFile = new File(newFilePath);
                    } else {
                        newFile = file;
                    }
                    newFiles.add(newFile);
                }
                newFileMap.put(key, newFiles);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileMap;
    }

    public static void deleteImgTmp(Map<String, ArrayList<File>> newFileMap) {
        for (String key : newFileMap.keySet()) {
            ArrayList<File> files = newFileMap.get(key);
            for (File file : files) {
                // 2018/8/4 只删除所压缩图片的临时产生文件
                if (file.exists() && file.getAbsolutePath().startsWith(tempRootDir))
                    file.delete();
            }
        }

    }

    public static void deleteTmpFolder(){
        File file = new File(tempRootDir);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if(f != null && f.exists())
                    f.delete();
            }
//            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
//        IbotnApplication.getInstance().getBaseContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)));
    }

    /*————————————————
        版权声明：本文为CSDN博主「陆游i」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/ls15256928597/article/details/78741757*/
    ///////////////////////////
    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
}

