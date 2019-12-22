package com.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Date:2019/10/18,13:54
 * author:jy
 */
public class BitmapUtil {
    /*————————————————
    版权声明：本文为CSDN博主「陆游i」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/ls15256928597/article/details/78741757*/
    static final String TAG = "BitmapUtil";
    /**
     * 旋转图片
     *
     * @param angle int
     * @param bitmap  Bitmap
     * @return Bitmap
     */
    public static Bitmap rotateBitmap(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        Log.d(TAG,"rotateImageView,angle:" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }
    public static void recycleBitmap(Bitmap bitmap) {
        if (null != bitmap && !bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
    public static double bitmapSize(String imagePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath,options);
        LogUtil.e(TAG,"bitmapSize  bitmap width = "+ bitmap.getWidth() + " height = "+ bitmap.getHeight());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        return baos.size()/1024F;
    }
    public static double bitmapSize(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        return baos.size()/1024F;
    }

    public static double bitmapMemorySize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount()/1024F;
        }
        // Pre HC-MR1
        return bitmap.getRowBytes() * bitmap.getHeight() / 1024F;
    }
    public static double bitmapSize03(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        return baos.toByteArray().length/1024F/1024F;
    }
    public static double bitmapSize02(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        return baos.size()/1024F/1024F;
    }
}
