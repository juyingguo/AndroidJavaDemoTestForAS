package com.sp.spmultipleapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.sp.spmultipleapp.MainActivity;
import com.sp.spmultipleapp.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 123 on 2018/4/19.
 */

public class TestBackgroundService extends Service {
    private String TAG = TestBackgroundService.class.getSimpleName();
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
    AtomicInteger aiCount;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate()");
//        readAndWriteFile2();


    }


    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG,"onStartCommand()");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,  "onStartCommand()，intent：" + intent + ",flags:" + flags + ",startId:" + startId);
        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind()");
        return new LocalBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind()");
        return super.onUnbind(intent);
    }

    public class LocalBinder extends Binder{
        public TestBackgroundService getService(){
            return TestBackgroundService.this;
         }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
        Log.d(TAG,"onDestroy(),will start ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            startForegroundService(new Intent(this, TestForegroundService.class));
        }else {
            startService(new Intent(this, TestForegroundService.class));
        }


    }
}
