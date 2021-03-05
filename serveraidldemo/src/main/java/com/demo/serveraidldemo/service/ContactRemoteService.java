package com.demo.serveraidldemo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.demo.serveraidldemo.IContactAidlInterface;
import com.demo.serveraidldemo.IContactAidlInterfaceCallback;

import static android.content.pm.PackageManager.PERMISSION_DENIED;

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
        //在这做权限验证,类似拦截器
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
                throws RemoteException {
            Log.d(TAG,"onTransact,interception,code:" + code + ",flags:" + flags);
            Log.d(TAG,"onTransact,getCallingUid():" + getCallingUid());
            Log.d(TAG,"onTransact,getCallingPid():" + getCallingPid());
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            if (packageName == null) {
                return false;
            }
            boolean checkPermission = checkPermission(ContactRemoteService.this, "com.demo.serveraidldemo.permission.ACCESS_CONTACT_SERVICE", packageName);
            if (!checkPermission) {
                Log.d(TAG,"onTransact,permission deny for uid:" + getCallingUid());
                return false;
            }
            return super.onTransact(code, data, reply, flags);
        }
    };
    private boolean checkPermission(Context context, String permName, String pkgName) {
        PackageManager pm = context.getPackageManager();
        if (PackageManager.PERMISSION_GRANTED == pm.checkPermission(permName, pkgName)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, ">>>>onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, ">>>>onBind()");
//         <!--添加访问远程服务的权限限制-->
        /*int check = checkCallingOrSelfPermission("com.demo.serveraidldemo.permission.ACCESS_CONTACT_SERVICE");
        if (check == PERMISSION_DENIED ){
            Log.d(TAG, ">>>>onBind(),PERMISSION_DENIED.");
            return null;
        }*/

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
