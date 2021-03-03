package com.demo.serveraidldemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.demo.serveraidldemo.IContactAidlInterface;
import com.demo.serveraidldemo.IContactAidlInterfaceCallback;

/**
 * create by jy on 20171030 <br/>
 * 联系人远程服务<br/>
 *
 */
public class ContactRemoteService extends Service {
    private static final String TAG = ContactRemoteService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.d(TAG, TAG + ">>>>onCreate()");
        super.onCreate();
    }

    IContactAidlInterface.Stub iBinder = new IContactAidlInterface.Stub() {
        @Override
        public void getContactByName(String contactName, IContactAidlInterfaceCallback callback) throws RemoteException {
            Log.d(TAG, TAG + ">>>>getContactByName>>contactName:" + contactName);
            Log.d(TAG, TAG + ">>>>getContactByName>>Thread.currentThread().getName():" + Thread.currentThread().getName());
            if (TextUtils.equals(contactName,"jim") ){
                callback.handleResult(contactName);
            }else {
                callback.handleResult(null);
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, ">>>>onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, ">>>>onBind()");
        return iBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, ">>>>onRebind()");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, ">>>>onUnbind()");
        iBinder = null;
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, ">>>>onDestroy()");

    }

}
