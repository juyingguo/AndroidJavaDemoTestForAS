package com.sp.spmultipleapp.service.keepaliveservice.one;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.utils.LogUtil;

public class KeepAliveTest01Service extends Service {

    private final String TAG = KeepAliveTest01Service.class.getSimpleName();
    public KeepAliveTest01Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d(TAG,"onStartCommand");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG,"onStartCommand");
        LogUtil.d(TAG,"onStartCommand，intent:" + intent);

        return START_STICKY/*super.onStartCommand(intent, flags, startId)*/;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG,"onDestroy");
    }
}
