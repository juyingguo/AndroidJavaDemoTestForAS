package com.sp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CoreBroadCastReceiver extends BroadcastReceiver {
    String TAG = CoreBroadCastReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive()");


    }
}
