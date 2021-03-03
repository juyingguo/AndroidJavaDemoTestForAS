package com.sp.spmultipleapp.activity.aidltest;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.demo.serveraidldemo.IContactAidlInterface;
import com.demo.serveraidldemo.IContactAidlInterfaceCallback;
import com.sp.spmultipleapp.R;
import com.utils.LogUtil;

public class AidlTestActivity extends AppCompatActivity {
    private final String TAG = "AidlTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_test);


        if (!isBoundRemoteContactService){
            bindRemoteContactService();
        }else {
            isExistContact(testName);
        }
    }
    String testName = "jim";
    /////////////////远程联系人服务相关/////////////start
    /** AIDL接口对象,用来进程间通信 */
    private IContactAidlInterface mIContactAidlInterface;
    private boolean isBoundRemoteContactService;
    /**
     * 绑定远程联系人服务。服务在com.ibotn.ibotnphone应用中。该服务的action="com.ibotn.ibotnphone.action.contact.remote.service"
     */
    private void bindRemoteContactService(){
        // 绑定服务，这里的service action非常重要，要跟server端定义的action一致
        //在4.4之前只需要setAction就可以绑定远程服务了；Android 5.0以上中出现的警告：Service Intent must be explicit,
        Intent service = new Intent();
        service.setAction("com.demo.serveraidldemo.action.contact.remote.service");
        service.setPackage("com.demo.serveraidldemo"); //该服务所在的应用包名，5.0以后需要
        bindService(service, mServiceConnection, BIND_AUTO_CREATE);
    }

    /** 绑定回调 */
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取AIDL接口对象，用来通信
            LogUtil.d(TAG,">>mServiceConnection>>onServiceConnected()>>" );
            mIContactAidlInterface = IContactAidlInterface.Stub.asInterface(service);
            isBoundRemoteContactService = true;

            isExistContact(testName);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.e(TAG ,  ">>mServiceConnection>>onServiceDisconnected()>>" );
            mIContactAidlInterface = null;
            isBoundRemoteContactService = false;
        }
    };
    private void unBindRemoteContactService(){
        LogUtil.d(TAG , ">>unBindRemoteContactService()>>isBoundRemoteContactService:" + isBoundRemoteContactService );
        if (isBoundRemoteContactService){
            unbindService(mServiceConnection);
        }
        isBoundRemoteContactService = false;
    }
    /**  */
    IContactAidlInterfaceCallback.Stub iContactAidlInterfaceCallback = new IContactAidlInterfaceCallback.Stub() {
        @Override
        public void handleResult(String contactName) throws RemoteException {
            LogUtil.d(TAG , ">>handleResult()>>testName:"+ testName +",contactName:" + contactName );
            if (TextUtils.equals(testName,contactName)){
            }else {
            }
//            unBindRemoteContactService();
        }
    };

    /**
     * 检查联系人是否存在
     * @param contactName 联系人名
     */
    private void isExistContact(String contactName){
        LogUtil.d(TAG , ">>isExistContact()>>contactName:" + contactName
                + "\n isBoundRemoteContactService:" + isBoundRemoteContactService );
        if (isBoundRemoteContactService && mIContactAidlInterface != null){
            try {
                mIContactAidlInterface.getContactByName(contactName,iContactAidlInterfaceCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
                LogUtil.e(TAG , ">>isExistContact()>>RemoteException:" + e.getMessage());
            }
        }

    }
    /////////////////远程联系人服务相关/////////////end

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindRemoteContactService();
    }
}
