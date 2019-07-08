package com.sp.spmultipleapp.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

public class TestForegroundService extends Service {
    private String TAG = TestForegroundService.class.getSimpleName();
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
    AtomicInteger aiCount;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate()");
//        readAndWriteFile2();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel();

            /*NotificationChannel channel = new NotificationChannel("id","name", NotificationManager.IMPORTANCE_LOW);

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            manager.createNotificationChannel(channel);

            Notification notification = new Notification.Builder(this,"id").build();

            startForeground(11, notification);*/

        }

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
        public TestForegroundService getService(){
            return TestForegroundService.this;
         }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }


    /**
     * ---------------------
     作者：i加加
     来源：CSDN
     原文：https://blog.csdn.net/sinat_20059415/article/details/80584487
     版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // 通知渠道的id
            String id = "my_channel_01";
            // 用户可以看到的通知渠道的名字.
            CharSequence name = "a";
            //         用户可以看到的通知渠道的描述
            String description = "b";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            //         配置通知渠道的属性
            mChannel.setDescription(description);
            //         设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            //         设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //         最后在notificationmanager中创建该通知渠道 //
            mNotificationManager.createNotificationChannel(mChannel);

            // 为该通知设置一个id
            int notifyID = 1;
            // 通知渠道的id
            String CHANNEL_ID = "my_channel_01";
            // Create a notification and set the notification channel.
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("New Message").setContentText("You've received new messages.")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setChannelId(CHANNEL_ID)
                    .build();
            startForeground(1, notification);
        }
    }



}

/*  //异常
04-13 15:53:29.294 20645-20645/com.sp.spmultipleapp E/AndroidRuntime: FATAL EXCEPTION: main
        Process: com.sp.spmultipleapp, PID: 20645
        android.app.RemoteServiceException: Bad notification for startForeground: java.lang.RuntimeException: invalid channel for service notification: Notification(channel=null pri=0 contentView=null vibrate=null sound=default defaults=0x1 flags=0x40 color=0x00000000 vis=PRIVATE)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1818)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loop(Looper.java:176)
        at android.app.ActivityThread.main(ActivityThread.java:6656)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:547)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:873)*/
