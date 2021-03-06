package com.sp.spmultipleapp.service.keepaliveservice.two;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.sp.spmultipleapp.IKeepAliveConnection;
import com.sp.spmultipleapp.R;
import com.utils.ServiceAliveUtils;

/**

* 主进程 双进程通讯

*

* @author LiGuangMin

* @time Created by 2018/8/17 11:26

*/

public class StepService extends Service {

   private final static String TAG = StepService.class.getSimpleName();

   private ServiceConnection mServiceConnection = new ServiceConnection() {

       @Override
       public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

           Log.d(TAG,"onServiceConnected");

           boolean isServiceRunning = ServiceAliveUtils.isServiceRunning(getApplicationContext(),getPackageName() + ".DownloadService");

           if(!isServiceRunning) {

               Intent i = new Intent(StepService.this, DownloadService.class);

               startService(i);

           }

       }

       @Override
       public void onServiceDisconnected(ComponentName componentName) {
           Log.d(TAG,"onServiceDisconnected");
           // 断开链接

           /**
            *  java.lang.IllegalStateException: Not allowed to start service Intent { cmp=com.sp.spmultipleapp/.service.keepaliveservice.two.GuardService }:
            *  app is in background uid UidRecord{2799b66 u0a401 SVC  bg:+1m0s37ms idle change:idle procs:1 seq(0,0,0)}
            */
//           startService(new Intent(StepService.this, GuardService.class));

           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               startForegroundService(new Intent(getApplicationContext(), GuardService.class));
           }else {
               startService(new Intent(getApplicationContext(), GuardService.class));
           }

           // 重新绑定
           bindService(new Intent(StepService.this, GuardService.class), mServiceConnection, Context.BIND_IMPORTANT);

       }

   };

 

   @Nullable
   @Override
   public IBinder onBind(Intent intent) {
       Log.d(TAG,"onBind");
        return new IKeepAliveConnection.Stub() {

       };

   }

   @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
   @Override
   public int onStartCommand(Intent intent, int flags, int startId) {
       Log.d(TAG,"onStartCommand");

//       startForeground(1, new Notification());
//       showNotification();


       // 绑定建立链接
       //bindService(new Intent(this, GuardService.class), mServiceConnection, Context.BIND_IMPORTANT);

       return START_STICKY;
   }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }


    void showNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }
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
            String id = "eink_channel_01";
            // 用户可以看到的通知渠道的名字.
            CharSequence name = "eink";
            // 用户可以看到的通知渠道的描述
            String description = "eink notify test";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            //配置通知渠道的属性
            mChannel.setDescription(description);
            //设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            //设置通知出现时的震动（如果 android 设备支持的话）
//            mChannel.enableVibration(true);
//            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            if (mNotificationManager != null) {
//                mNotificationManager.createNotificationChannel(mChannel);
            }

            // 为该通知设置一个id
            int notifyID = 1;
            // 通知渠道的id
            String CHANNEL_ID = "my_channel_01";
            // Create a notification and set the notification channel.
//            Notification notification = new Notification.Builder(this)
            Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setContentTitle("New Message").setContentText("You've received new messages.")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setChannelId(CHANNEL_ID)
                    .build();
            startForeground(1, notification);
//            stopForeground(true);
        }
    }

    private void lunchForeground(){
        String CHANNEL_ID = "eink_channel_01";
        // Create a notification and set the notification channel.
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("New Message").setContentText("You've received new messages.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setChannelId(CHANNEL_ID)
                .build();
        startForeground(1, notification);
    }
}