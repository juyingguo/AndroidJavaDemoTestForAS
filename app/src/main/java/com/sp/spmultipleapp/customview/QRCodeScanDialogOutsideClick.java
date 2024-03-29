package com.sp.spmultipleapp.customview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.sp.spmultipleapp.R;
import com.zxing.android.MessageIDs;
import com.zxing.android.base.DecodeInterface;
import com.zxing.android.camera.CameraManager;
import com.zxing.android.decoding.BaseCaptureActivityHandler;
import com.zxing.android.view.ViewfinderView;

import java.util.Vector;

/**
 * Date:2020/12/31,9:51
 * author:jy QRCodeScan
 * <p>
 *      验证课题：：
 *     Android在弹窗不消失的情况下依然能响应外部事件，且能够SurfaceView能够预览camera画面。
 *     是可行的。
 */
public class QRCodeScanDialogOutsideClick extends Dialog implements DecodeInterface, SurfaceHolder.Callback {
    private final String TAG = "QRCodeScanDialogOutsideClick";
    private BaseCaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private SurfaceView surfaceView;
    private ImageView imageViewRotate;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    // private static final float BEEP_VOLUME = 0.10f;
    CameraManager cameraManager;
    private RelativeLayout rlDynamicPosition;
    Activity activity;
    public static String ACTION_RESULT = "ACTION_RESULT";
    public static String ACTION_DISMISS_DIALOG = "ACTION_DISMISS_DIALOG";
    private final int DEFAULT_X = 350 + 200;
    private final int DEFAULT_Y = 170;
    /** 默认显示大小类型为0*/
    private final int DEFAULT_DISPLAY_SIZE_TYPE = 0;
    int x = DEFAULT_X;
    int y = DEFAULT_Y;
    int mDisplaySizeType = DEFAULT_DISPLAY_SIZE_TYPE;
    /**
     * should subclass of Activity  call this method
     * @param activity subclass of Activity
     */
    @SuppressLint("LongLogTag")
    public QRCodeScanDialogOutsideClick(Activity activity){
        super(activity,R.style.Theme_CustomDialog_Shape_Transparent_Fillet);
        this.activity = activity;
        Log.d(TAG,"QRCodeScanDialog constructor call.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_not_canceled_qr_code_scan);
        surfaceView = findViewById(R.id.surfaceview);
        viewfinderView = findViewById(R.id.viewfinderview);
        rlDynamicPosition = findViewById(R.id.rl_dynamic_position);
        imageViewRotate = findViewById(R.id.iv_rotate);

        setCanceledOnTouchOutside(false);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        hasSurface = false;
        startCameraOperation();
        setOnDismissListener(new OnDismissListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (handler != null) {
                    handler.quitSynchronously();
                    handler = null;
                }
                Log.i(TAG, "onDismiss().");
                imageViewRotate.clearAnimation();
                cameraManager.closeDriver(true);
                activity.unregisterReceiver(receiver);
            }
        });
        hideNavigationBar();

        IntentFilter filter = new IntentFilter(QRCodeScanDialogOutsideClick.ACTION_DISMISS_DIALOG);
        this.activity.registerReceiver(receiver,filter);
        animationRotateTest();

        displayByType();
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @SuppressLint("LongLogTag")
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG,"onReceive,action:" + intent.getAction());
            if (TextUtils.equals(intent.getAction(), QRCodeScanDialogOutsideClick.ACTION_DISMISS_DIALOG)){
                dismiss();
            }
        }
    };
    private void hideNavigationBar(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void animationRotateTest() {
        Animation operatingAnim = AnimationUtils.loadAnimation(this.activity,R.anim.anim_rotation_image);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        operatingAnim.setInterpolator(linearInterpolator);
        imageViewRotate.startAnimation(operatingAnim);
    }

    @SuppressLint("LongLogTag")
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
    @SuppressLint("LongLogTag")
    private void initCamera(SurfaceHolder surfaceHolder) {
        Log.d(TAG,"initCamera");
        openCamera(surfaceHolder);

        if (handler == null) {
            handler = new BaseCaptureActivityHandler(this, decodeFormats, characterSet);
        }

    }

    @SuppressLint("LongLogTag")
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

    @SuppressLint("LongLogTag")
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG,"surfaceCreated");
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @SuppressLint("LongLogTag")
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
        showResult(obj, barcode);
    }

    @SuppressLint("LongLogTag")
    private void showResult(final Result rawResult, Bitmap barcode) {
        String result = rawResult.getText();
        //url = "http://edu.ibotn.com/download/test/孟母三迁_孟母三迁_1080P在线观看_腾讯视频.mp4";
        //url = "http://edu.ibotn.com/download/test/1516343632429.wav";
        //url = "http://edu.ibotn.com/download/test/大王叫我来巡山-贾乃亮,甜馨.mp3";
        //url = "http://edu.ibotn.com/download/test/kq151511779435544477.png";
        //url = "http://edu.ibotn.com/download/test/test_pic_3.jpg";

        Log.i(TAG, "showResult,result-->" + result);
//        WebViewJsJavaCallEachOtherActivity.getInstance().showQrResult(result);
        Intent intent = new Intent();
        intent.setAction(ACTION_RESULT);
        intent.putExtra("result",result);
        activity.sendBroadcast(intent);
        restartPreviewAfterDelay(200L);
    }
    public void restartPreviewAfterDelay(long delayMS) {
        if (handler != null) {
            handler.sendEmptyMessageDelayed(MessageIDs.restart_preview, delayMS);
        }
    }

    @SuppressLint("LongLogTag")
    private void displayByType() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) surfaceView.getLayoutParams();
        if (mDisplaySizeType == 0){
            layoutParams.width = 180;
            layoutParams.height = 120;
        }else if (mDisplaySizeType == 1){
            layoutParams.width = 220;
            layoutParams.height = 150;
        }else if (mDisplaySizeType == 2){
            layoutParams.width = 260;
            layoutParams.height = 180;
        }
        surfaceView.setLayoutParams(layoutParams);
    }

    public void showAtLocationInActivity() {
        show();
    }

    @SuppressLint("LongLogTag")
    public void showAtLocationInActivityParam(int x,int y) {
//        this.x = Integer.parseInt(x + );
        this.x = x;
        this.y = y;
        Log.d(TAG,"showAtLocationInActivityParam(),this.x:" + this.x + ",this.y:" + this.y);
        show();
    }

    /**
     * 可以指定坐标和显示扫描框大小类型
     * @param x x坐标
     * @param y y坐标
     * @param displaySizeType 0对应默认大小的框(框直径220)，1值对应稍大些的框(框直径270),2值对应稍大些的框(框直径320),
     */
    @SuppressLint("LongLogTag")
    public void showAtLocationInActivityParam(int x,int y,int displaySizeType) {
        if (displaySizeType <=0) displaySizeType = 0;
        else if (displaySizeType == 1)
            displaySizeType = 1;
        else displaySizeType = 2;
        this.x = x;
        this.y = y;
        this.mDisplaySizeType = displaySizeType;
        Log.d(TAG,"showAtLocationInActivityParam(),this.x:" + this.x + ",this.y:" + this.y + ",this.mDisplaySizeType:" + this.mDisplaySizeType);
        show();
    }

    @SuppressLint("LongLogTag")
    @Override
    public void show() {
        super.show();
//        getWindow().setDimAmount(0);//设置背景透明或者通过样式设置<item name="android:backgroundDimEnabled">false</item>
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.width = 300/*activity.getResources().getDisplayMetrics().widthPixels / 3*/;
//        layoutParams.height = 300/*activity.getResources().getDisplayMetrics().heightPixels / 4 * 3*/;
        /**
         * 窗口及摄像头预览SurfaceView大小配置：
         * SurfaceView大小要和摄像头分辨率或屏幕分辨率宽高比例保存一致，否则会出现变形，比例差别越大，变形月明显。
         * 1280/720 = 1.777;
         * 1024/600 = 1.706
         * a.
         *  220，220
         * SurfaceView
         *             android:layout_width="180dp"
         *             android:layout_height="120dp"
         * b.
         * layoutParams.width = 270
         * layoutParams.height = 270 还有些大。
         *  SurfaceView
         *             android:layout_width="220dp"
         *             android:layout_height="150dp"
         * c.
         * layoutParams.width = 320
         * layoutParams.height = 320 还有些大。
         *  SurfaceView
         *             android:layout_width="260dp"
         *             android:layout_height="180dp"
         * */
        if (mDisplaySizeType == 0){
            layoutParams.width = 220;
            layoutParams.height = 220;
        }else if (mDisplaySizeType == 1){
            layoutParams.width = 270;
            layoutParams.height = 270;
        }else if (mDisplaySizeType == 2){
            layoutParams.width = 320;
            layoutParams.height = 320;
        }
        layoutParams.gravity = Gravity.TOP | Gravity.START;
        layoutParams.x = this.x;
        layoutParams.y = this.y;
        Log.d(TAG,"show(),layoutParams.x:" + layoutParams.x  + " layoutParams.y:" + layoutParams.y);
//        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);

    }

}
