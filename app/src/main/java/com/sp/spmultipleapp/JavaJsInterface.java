package com.sp.spmultipleapp;

import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.sp.spmultipleapp.activity.CaptureActivity;
import com.sp.spmultipleapp.application.CoreApplication;

/**
 * Date:2020/12/28,15:45
 * author:jy
 */
public class JavaJsInterface {
    String TAG  = "JavaJsInterface";
    @JavascriptInterface
    public void startQrCodeScan() {
        Log.d(TAG,"startQrCodeScan.");
        Intent intent = new Intent(CoreApplication.getInstance(), CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CoreApplication.getInstance().startActivity(intent);
    }
}
