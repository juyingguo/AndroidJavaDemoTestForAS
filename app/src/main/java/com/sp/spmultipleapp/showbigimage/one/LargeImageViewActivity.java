package com.sp.spmultipleapp.showbigimage.one;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
public class LargeImageViewActivity extends AppCompatActivity
{
    private LargeImageView mLargeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image_view);

        mLargeImageView = (LargeImageView) findViewById(R.id.id_largetImageview);
        try
        {
            InputStream inputStream = getAssets().open("qm.jpg");
            mLargeImageView.setInputStream(inputStream);

        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

}
