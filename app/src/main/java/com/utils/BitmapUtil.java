package com.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

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
}
