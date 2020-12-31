package com.sp.spmultipleapp.customview;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.sp.spmultipleapp.R;
import com.sp.spmultipleapp.activity.WebViewJsJavaCallEachOtherActivity;
import com.zxing.android.base.DecodeInterface;
import com.zxing.android.camera.CameraManager;
import com.zxing.android.decoding.BaseCaptureActivityHandler;
import com.zxing.android.decoding.InactivityTimer;
import com.zxing.android.view.ViewfinderView;

import java.util.Vector;

/**
 * Date:2020/12/31,9:51
 * author:jy QRCodeScan
 * <p>should subclass of Activity  call QRCodeScanPopupWindow
 */
public class QRCodeScanDialog extends Dialog implements DecodeInterface, SurfaceHolder.Callback {
    private final String TAG = "QRCodeScanPopupWindow";
    private BaseCaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private SurfaceView surfaceView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    // private static final float BEEP_VOLUME = 0.10f;
    CameraManager cameraManager;
    private RelativeLayout rlDynamicPosition;
    Activity activity;
    private InactivityTimer inactivityTimer;
    /**
     * should subclass of Activity  call this method
     * @param activity subclass of Activity
     */
    public QRCodeScanDialog(Activity activity){
        super(activity);
        Log.d(TAG,"QRCodeScanDialog constructor call.");
        setContentView(R.layout.dialog_qr_code_scan);
        this.activity = activity;
        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinderview);
        rlDynamicPosition = (RelativeLayout) findViewById(R.id.rl_dynamic_position);

        inactivityTimer = new InactivityTimer(activity);

        hasSurface = false;
        startCameraOperation();
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (handler != null) {
                    handler.quitSynchronously();
                    handler = null;
                }
                Log.i(TAG, "onDismiss().");
                cameraManager.closeDriver(true);
                inactivityTimer.shutdown();
            }
        });
        //添加一下三行，Android在弹窗不消失的情况下依然能响应外部事件，且能够SurfaceView能够预览camera画面。
        setCanceledOnTouchOutside(false);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
    }
    private void startCameraOperation(){
        Log.d(TAG,"startCameraOperation call.");
        cameraManager = new CameraManager(activity.getApplication());

        viewfinderView.setCameraManager(cameraManager);

        SurfaceHolder surfaceHolder = surfaceView.getHolder();

        if (hasSurface) {
            Log.d(TAG,"startCameraOperation hasSurface: " + hasSurface + " to call initCamera.");
            initCamera(surfaceHolder);
        } else {
            Log.d(TAG,"startCameraOperation to call surfaceHolder.addCallback(this)");
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

    }
    private void initCamera(SurfaceHolder surfaceHolder) {
        Log.d(TAG,"initCamera");
        openCamera(surfaceHolder);

        if (handler == null) {
            handler = new BaseCaptureActivityHandler(this, decodeFormats, characterSet);
        }

    }

    private boolean openCamera(SurfaceHolder surfaceHolder){
        Log.d(TAG,"openCamera.");
        try {
            cameraManager.openDriver(surfaceHolder);
            return true;
        }catch (Exception e){//如果此处报异常，说明没有获取到权限
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG,"surfaceCreated");
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        Log.d(TAG,"openCamera,reset hasSurface to " + hasSurface);
    }
    @Override
    public CameraManager getCameraManager() {
        return cameraManager;
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        try {
            viewfinderView.drawViewfinder();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void mySetResult(int resultCode, Intent data){

    }
    public void myFinish(){
        dismiss();
    }
    @Override
    public void myStartActivity(Intent intent){
    }
    public void handleDecode(Result obj, Bitmap barcode) {
        inactivityTimer.onActivity();
        showResult(obj, barcode);
    }

    private void showResult(final Result rawResult, Bitmap barcode) {
        String result = rawResult.getText();
        //url = "http://edu.ibotn.com/download/test/孟母三迁_孟母三迁_1080P在线观看_腾讯视频.mp4";
        //url = "http://edu.ibotn.com/download/test/1516343632429.wav";
        //url = "http://edu.ibotn.com/download/test/大王叫我来巡山-贾乃亮,甜馨.mp3";
        //url = "http://edu.ibotn.com/download/test/kq151511779435544477.png";
        //url = "http://edu.ibotn.com/download/test/test_pic_3.jpg";

        Log.i(TAG, "showResult,result-->" + result);
        WebViewJsJavaCallEachOtherActivity.showQrResult(result);
        dismiss();
    }

    public void showAtLocationInActivity() {
        show();
    }
}
