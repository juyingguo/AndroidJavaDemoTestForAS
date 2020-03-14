package com.sp.spmultipleapp.showimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;

//import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoView;
import com.sp.spmultipleapp.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date:2020/3/14,11:13
 * author:jy
 */
public class ShowScaleImageWithPhotoViewActivity extends Activity {
    PhotoView photoView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_with_photoview);

        photoView  = findViewById(R.id.photoView);

       loadImage();
    }

    private void loadImage() {
        try {
//            InputStream inputStream = getAssets().open("tangyan.jpg");
            InputStream inputStream = getAssets().open("test_pic_compress/big_img_test.jpg");
//            InputStream inputStream = getAssets().open("qm.jpg");

            //获得图片的宽、高
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, tmpOptions);
            photoView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
