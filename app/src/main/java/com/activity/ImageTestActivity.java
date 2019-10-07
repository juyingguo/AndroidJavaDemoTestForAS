package com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.photowall.PhotoWallTestActivity;
import com.sp.spmultipleapp.R;
import com.viewtest.verifyviewmethod.one.InvalidateTestActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ImageTestActivity extends AppCompatActivity {
    Unbinder butterKnifeBind = null;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

    }

    @OnClick({
            R.id.btn_activity_lrucache
            ,R.id.btn_activity_photowall
            ,R.id.btn_universal_imageload
    })
    public void clickView(View view) {
        if (view.getId() == R.id.btn_activity_lrucache){
            startActivity(new Intent(mContext , LruCacheTestActivity.class));
        }else if (view.getId() == R.id.btn_activity_photowall){
            startActivity(new Intent(mContext, PhotoWallTestActivity.class));
        }else if (view.getId() == R.id.btn_universal_imageload){
            startActivity(new Intent(mContext, UniversalImageLoaderTestActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }
}
