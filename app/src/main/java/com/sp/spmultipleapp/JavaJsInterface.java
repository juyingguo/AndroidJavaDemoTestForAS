package com.sp.spmultipleapp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.sp.spmultipleapp.activity.CaptureActivity;
import com.sp.spmultipleapp.activity.WebViewJsJavaCallEachOtherActivity;
import com.sp.spmultipleapp.application.CoreApplication;
import com.sp.spmultipleapp.customview.QRCodeScanDialogNotCanceled;
import com.sp.spmultipleapp.customview.QRCodeScanPopupWindow;

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
    @JavascriptInterface
    public void startQrCodeScanWithPopupWindow() {
        Log.d(TAG,"startQrCodeScanWithPopupWindow.");
        QRCodeScanPopupWindow window = new QRCodeScanPopupWindow(WebViewJsJavaCallEachOtherActivity.getInstance()
                , View.inflate(WebViewJsJavaCallEachOtherActivity.getInstance(), R.layout.popupwindow_qr_code_scan,null));
        window.showAtLocationInActivity();
    }@JavascriptInterface
    public void startQrCodeScanWithDialog() {
        Log.d(TAG,"startQrCodeScanWithDialog.");
        QRCodeScanDialogNotCanceled dialogNotCanceled = new QRCodeScanDialogNotCanceled(WebViewJsJavaCallEachOtherActivity.getInstance());
        dialogNotCanceled.showAtLocationInActivity();
    }
}
