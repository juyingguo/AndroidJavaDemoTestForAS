package com.sp.spmultipleapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sp.spmultipleapp.R;
import com.sp.spmultipleapp.service.keepaliveservice.two.DownloadService;
import com.sp.spmultipleapp.service.keepaliveservice.two.StepService;

public class KeepAliveTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_alive_test);

        //startActivity(new Intent(mContext, KeepAliveServiceActivity.class));
    }

    public void clickForKeepAliveTwo(View view) {
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);

//        startAllGuardServices();
    }

    /**

     * 开启所有守护Service

     */

    private void startAllGuardServices() {

        startService(new Intent(this, StepService.class));

//        startService(new Intent(this, GuardService.class));

    }
}
