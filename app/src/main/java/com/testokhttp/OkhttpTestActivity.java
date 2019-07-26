package com.testokhttp;

import android.app.Activity;
import android.os.Bundle;

import com.sp.spmultipleapp.R;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class OkhttpTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_test);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .cache(new Cache(getExternalCacheDir(),10*1024*1024))
                .build();

    }
}
