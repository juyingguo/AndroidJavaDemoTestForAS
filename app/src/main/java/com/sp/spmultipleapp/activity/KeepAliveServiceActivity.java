package com.sp.spmultipleapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sp.spmultipleapp.R;
import com.sp.spmultipleapp.service.keepaliveservice.one.KeepAliveTest01Service;

public class KeepAliveServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_alive_service);


        startService(new Intent(this, KeepAliveTest01Service.class));
    }
}
