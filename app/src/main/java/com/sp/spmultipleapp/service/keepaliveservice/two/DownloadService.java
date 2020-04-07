package com.sp.spmultipleapp.service.keepaliveservice.two;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.sp.spmultipleapp.R;
import com.utils.ThreadUtils;

/**
 * Date:2020/3/30,14:14
 * author:jy
 */
public class DownloadService extends Service {
    String TAG = "DownloadService";
    boolean mFlag = true;
    int count ;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");

        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                while (mFlag){
                    Log.d(TAG,"sub thread running ...:" + (count++));
                    SystemClock.sleep(5000);
                }
            }
        });

        lunchForeground();
    }

    private void lunchForeground(){
        Log.d(TAG,"lunchForeground()");
        String CHANNEL_ID = "eink_channel_01";
        // Create a notification and set the notification channel.
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("download").setContentText("download server running.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setChannelId(CHANNEL_ID)
                .build();
        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFlag = false;
        Log.d(TAG,"onDestroy");
    }
}
