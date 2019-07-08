package com.sp.spmultipleapp;

import android.content.Context;
import android.util.Log;

import util.NetUtils;

public class NetDetectHelper {
    private static String TAG = NetDetectHelper.class.getSimpleName();

    public static void detectNet(Context context){

        String ipAddress = NetUtils.getIPAddress(context);

        Log.d(TAG,"detectNet>>>ipAddress:" + ipAddress);
    }
}
