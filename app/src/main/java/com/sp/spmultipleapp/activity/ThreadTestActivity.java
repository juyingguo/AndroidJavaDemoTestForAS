package com.sp.spmultipleapp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sp.spmultipleapp.R;
import com.utils.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadTestActivity extends AppCompatActivity {
    private String TAG = "ThreadTestActivity";
    @BindView(R.id.textView2)
    TextView textView2;

    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);
        ButterKnife.bind(this);

        textView2.setText("test butter knife");
    }
    @OnClick({R.id.btn_create_thread_test,R.id.btn_create_thread_pool_test})
    public void click(View view){
        if (view.getId() == R.id.btn_create_thread_test){
            for (int i = 0; i <5 ;i++){
                ThreadUtils.runOnBackgroundThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG,"click(),Thread.currentThread().getName():" + Thread.currentThread().getName() + " and thread id:" + Thread.currentThread().getId());
                        SystemClock.sleep(10*1000);
                    }
                });
            }
        }else if (view.getId() == R.id.btn_create_thread_pool_test){
            for (int i = 0; i <5 ;i++){
                newFixedThreadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG,"newFixedThreadPool run(),Thread.currentThread().getName():" + Thread.currentThread().getName() + " and thread id:" + Thread.currentThread().getId());
                        SystemClock.sleep(10*1000);
                    }
                });
            }
        }
    }
}
