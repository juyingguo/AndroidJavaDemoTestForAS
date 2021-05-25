package com.sp.spmultipleapp.application;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.sp.spmultipleapp.service.SdcardReadWriteDealService;
import com.sp.spmultipleapp.service.TestService;

public class CoreApplication extends Application {
    String TAG = CoreApplication.class.getSimpleName();

    private static Context mContext;
    private AppLifecycle appLifecycle;
    public static Context getInstance() {
        return mContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        Log.d(TAG,"onCreate()");
//        Intent intent = new Intent(this, SdcardReadWriteDealService.class);
//        bindService(intent,connect, Service.BIND_AUTO_CREATE);

        /////////////////////
//        startService(new Intent(this, TestService.class));

//        startSeviceByAlarm();
        appLifecycle = new AppLifecycle();
        registerActivityLifecycleCallbacks(appLifecycle);
    }
    public AppLifecycle getAppLifecycle() {
        return appLifecycle;
    }
    private void startSeviceByAlarm() {
        Intent intent = new Intent(getApplicationContext(), TestService.class);
        PendingIntent sender=PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
        if (alarm !=null){
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),60*1000 + 10,sender);
        }

    }

    private ServiceConnection connect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SdcardReadWriteDealService.MyBinder myBinder = (SdcardReadWriteDealService.MyBinder)iBinder;
            Log.d(TAG,"onServiceConnected()");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG,"onServiceDisconnected()");
        }
    };
    @Override
    public void onLowMemory() {
        Log.d(TAG,"onLowMemory()");
        super.onLowMemory();

    }

    @Override
    public void onTerminate() {
        Log.d(TAG,"onTerminate()");
        super.onTerminate();

    }
}
