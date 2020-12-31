package com.zxing.android.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;

import com.google.zxing.Result;
import com.zxing.android.camera.CameraManager;
import com.zxing.android.view.ViewfinderView;

/**
 * Date:2020/12/30,17:25
 * author:jy
 * <p>base interface for common decode;
 */
public interface DecodeInterface {
    ViewfinderView getViewfinderView();

    /**
     * should call {@link ViewfinderView#drawViewfinder()} in this method.
     */
    void drawViewfinder();
    CameraManager getCameraManager();
    void handleDecode(Result obj, Bitmap barcode);

    /**
     *
     * copy from {@link android.app.Activity#setResult(int, Intent)},change name to mySetResult
     *  <p> if your activity implement {@link DecodeInterface},just call {@link android.app.Activity#setResult(int, Intent)} in this method.
     *  otherwise do something by yourself.
     */
    void mySetResult(int resultCode, Intent data);
    /**
     *
     * copy from {@link Activity#finish()},change name to myFinish
     *  <p> if your activity implement {@link DecodeInterface},just call {@link Activity#finish()} in this method.
     *  otherwise close display by yourself.
     */
    void myFinish();
    /**
     *
     * copy from {@link Activity#startActivity(Intent, Bundle)} ,change name to myFinish
     *  <p> if your activity implement {@link DecodeInterface},just call {@link Activity#startActivity(Intent)} in this method.
     *  otherwise do something by yourself.
     */
    void myStartActivity(Intent intent);
    Handler getHandler();
}
