package com.sp.spmultipleapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
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

public class TestService extends Service {
    private String TAG = TestService.class.getSimpleName();
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
    AtomicInteger aiCount;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate()");
//        readAndWriteFile2();

        /*Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = new Intent(this, MainActivity.class);

        builder.setContentIntent(PendingIntent. getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
        .setContentTitle("下拉列表中的Title") // 设置下拉列表里的标题
        .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
        .setContentText("要显示的内容") // 设置上下文内容
        .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间

        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音

        startForeground(100,notification);*/
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
        public TestService getService(){
            return TestService.this;
         }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }
}
