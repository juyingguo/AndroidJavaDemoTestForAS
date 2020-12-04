package com.sp.spmultipleapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.utils.NetUtils;
import com.utils.WifiUtils;
import com.utils.ThreadUtils;
public class CoreBroadCastReceiver extends BroadcastReceiver {
    String TAG = CoreBroadCastReceiver.class.getSimpleName();
    Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive()");
        mContext = context;
        String action = intent.getAction();
        Log.d(TAG,"onReceive>>action:" + action);
        if (TextUtils.equals(Intent.ACTION_BOOT_COMPLETED,action)){
            /*ThreadUtils.runOnUiThreadDelay(new Runnable() {
                @Override
                public void run() {

                    WifiUtils.createAp(true);
                }
            },10000);*/
        }else if (TextUtils.equals(ConnectivityManager.CONNECTIVITY_ACTION,action)){
            boolean networkAvailable = NetUtils.isNetworkConnected(context);
            Log.d(TAG,"onReceive>>networkAvailable:" + networkAvailable);
            Toast.makeText(context,"网络情况：" + networkAvailable,Toast.LENGTH_LONG).show();
        }
    }
}
