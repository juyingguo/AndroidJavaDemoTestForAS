package com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.photowall.PhotoWallTestActivity;
import com.sp.spmultipleapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CameraPictureVideoTestActivity extends AppCompatActivity {
    Unbinder butterKnifeBind = null;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

    }

    @OnClick({
            R.id.btn_take_photo
            ,R.id.btn_picture_scale_compress

    })
    public void clickView(View view) {
        if (view.getId() == R.id.btn_take_photo){
            startActivity(new Intent(mContext , CameraTakePhotoTestActivity.class));
        }else if (view.getId() == R.id.btn_picture_scale_compress){
            startActivity(new Intent(mContext , PictureScaleCompressTestActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }
}
