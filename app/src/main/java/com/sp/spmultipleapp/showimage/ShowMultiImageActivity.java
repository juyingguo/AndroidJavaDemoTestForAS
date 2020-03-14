package com.sp.spmultipleapp.showimage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sp.spmultipleapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Date:2020/3/14,11:13
 * author:jy
 */
public class ShowMultiImageActivity extends Activity {
    Unbinder butterKnifeBind = null;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_multiple_type_image);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

    }

    @OnClick({
            R.id.btn_show_scale_image
            ,R.id.btn_show_scale_image_with_photoview
    })
    public void clickView(View view) {
        if (view.getId() == R.id.btn_show_scale_image){
            startActivity(new Intent(mContext , ShowScaleImageActivity.class));
        }else if (view.getId() == R.id.btn_show_scale_image_with_photoview){
            startActivity(new Intent(mContext , ShowScaleImageWithPhotoViewActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }
}
