package com.sp.spmultipleapp.showbigimage.one;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sp.spmultipleapp.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Android 高清加载巨图方案 拒绝压缩图片
 * 		「鸿洋_」
 * 		https://blog.csdn.net/lmj623565791/article/details/49300989
 *
 * 		参考链接
 * 		http://code.almeros.com/android-multitouch-gesture-detectors#.VibzzhArJXg
 * 		https://github.com/rharter/android-gesture-detectors
 * 		https://github.com/johnnylambada/WorldMap	//在实际的项目中，可能会有更多的需求，比如增加放大、缩小；增加快滑手势等等
 * 		————————————————
 * 		版权声明：本文为CSDN博主「鸿洋_」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 		原文链接：https://blog.csdn.net/lmj623565791/article/details/49300989
 */
public class BitmapRegionDecoderTestActivity extends AppCompatActivity
{
    private ImageView mLargeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_region_decoder);

        mLargeImageView = (ImageView) findViewById(R.id.id_bitmap_region_decoder);
        try
        {
            InputStream inputStream = getAssets().open("tangyan.jpg");

            //获得图片的宽、高
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, tmpOptions);
            int width = tmpOptions.outWidth;
            int height = tmpOptions.outHeight;

            //设置显示图片的中心区域
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(width / 2 - 100, height / 2 - 100, width / 2 + 100, height /*/ 2 + 100*/), options);
            mLargeImageView.setImageBitmap(bitmap);


        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

}
