package com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.photowall.PhotoWallTestActivity;
import com.sp.spmultipleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UniversalImageLoaderTestActivity extends AppCompatActivity {
    Unbinder butterKnifeBind = null;

    private Context mContext;
    @BindView(R.id.imageView)
    public ImageView imageView;

    private String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570106277004&di=59df49988169cccfddedfcd45cbfaa74&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20161220%2F2e4f92f816cb46b192b87aa45446e046_th.jpg";
    private String imageUrl02 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570106276989&di=41dd2795f11855097ac59f361e8b569d&imgtype=0&src=http%3A%2F%2Fimg3.redocn.com%2F20120331%2FRedocn_2012033106470673.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_imageload_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
        DisplayImageOptions imageOptions = DisplayImageOptions.createSimple();
        ImageLoader.getInstance().displayImage(imageUrl, imageView, imageOptions);
    }

    public void clickView(View view) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }
}
