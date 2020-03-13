package com.sp.spmultipleapp.showbigimage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sp.spmultipleapp.R;
import com.sp.spmultipleapp.showbigimage.one.LargeImageViewActivity;
import com.sp.spmultipleapp.showbigimage.one.LargeImageViewWithScaleActivity;
import com.sp.spmultipleapp.showbigimage.one.LargeImageWithGalleryActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShowBigImageTestActivity extends AppCompatActivity {
    Unbinder butterKnifeBind = null;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_big_image_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

    }

    @OnClick({
            R.id.btn_large_image_view
            ,R.id.btn_large_image_view_with_scale
            ,R.id.btn_load_image_with_gallery
    })
    public void clickView(View view) {
        if (view.getId() == R.id.btn_large_image_view){
            startActivity(new Intent(mContext , LargeImageViewActivity.class));
        }else if (view.getId() == R.id.btn_large_image_view_with_scale){
            startActivity(new Intent(mContext , LargeImageViewWithScaleActivity.class));
        }else if (view.getId() == R.id.btn_load_image_with_gallery){
            startActivity(new Intent(mContext , LargeImageWithGalleryActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }
}
