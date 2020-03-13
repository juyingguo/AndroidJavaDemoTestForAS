package com.sp.spmultipleapp.showbigimage.one;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sp.spmultipleapp.R;

import java.io.File;

/**
 */
public class LargeImageWithGalleryActivity extends AppCompatActivity
{
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image_with_gallery);

        mButton = (Button) findViewById(R.id.btn_load_image_with_gallery);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });

    }

    private final String IMAGE_TYPE = "image/*";
    public static final int IMAGE_REQUEST_CODE = 0x102;
    public void openAlbum(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        String imageUri = "assets://caimogu.jpg"; // from assets
//        String imageUri = "android.resource://" + getPackageName() + "/" + R.raw.caimogu; // from assets
//        Uri uri = Uri.parse(imageUri);
        Uri uri = Uri.fromFile(new File("/storage/sd-ext/testdir/caimogu.jpg"));
        intent.setDataAndType(uri,IMAGE_TYPE);
        if (Build.VERSION.SDK_INT <19) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }else {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        }
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

}
