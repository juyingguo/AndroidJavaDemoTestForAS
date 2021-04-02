package com.sp.spmultipleapp.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.sp.spmultipleapp.R;
import com.zxing.android.MessageIDs;
import com.zxing.android.base.DecodeInterface;
import com.zxing.android.camera.CameraManager;
import com.zxing.android.decoding.BaseCaptureActivityHandler;
import com.zxing.android.decoding.InactivityTimer;
import com.zxing.android.view.ViewfinderView;

import java.io.IOException;
import java.util.Vector;

/**
 * 1.copy from {@link CaptureActivity},but local handler use {@link BaseCaptureActivityHandler}<br/>
 * 2.一次扫描识别完成后，再次启动下一轮的扫描识别，需要调用{@link #restartPreviewAfterDelay}
 *
 */
public class CaptureActivityNew extends BaseActivity implements DecodeInterface, Callback, View.OnClickListener {
	private static final String TAG = "CaptureActivityNew";

	private BaseCaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private SurfaceView surfaceView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	// private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	CameraManager cameraManager;
	private RelativeLayout rlDynamicPosition;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
//		setAlpha();
		setContentView(R.layout.activity_capture_new);
		surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinderview);
        rlDynamicPosition = (RelativeLayout) findViewById(R.id.rl_dynamic_position);

		//Window window = getWindow();
		//window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);

//		dynamicPosition();
//		setAlpha();
	}

    @Override
	protected void onResume() {
		super.onResume();
		//if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
		//	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//}
		// CameraManager.init(getApplication());
		startCameraOperation();
	}

	private void startCameraOperation(){
		cameraManager = new CameraManager(getApplication());

		viewfinderView.setCameraManager(cameraManager);

		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		Log.i("jlzou", "jlzou onPause");
		cameraManager.closeDriver(true/*,ibotnCameraService*/);
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();

	}

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        dynamicPosition();
    }

    /**
     * 动态指定camera二维码识别框的位置
     */
    private void dynamicPosition() {

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlDynamicPosition.getLayoutParams();
        layoutParams.leftMargin = rlDynamicPosition.getLeft()+500;
        layoutParams.topMargin = rlDynamicPosition.getTop()+250;
//        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        rlDynamicPosition.setLayoutParams(layoutParams);
    }

    /**
     * 清单文件中activity节点配置透明样式后，代码就不用配置了。
     */
    private void setAlpha() {
        Window window=getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.flags=WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        //这句就是设置窗口里控件的透明度的．０.０全透明．１.０不透明．
        wl.alpha=0.3f;
        window.setAttributes(wl);
    }

	private void initCamera(SurfaceHolder surfaceHolder) {
        openCamera(surfaceHolder);

		if (handler == null) {
			handler = new BaseCaptureActivityHandler(this, decodeFormats, characterSet);
		}

	}

	private boolean openCamera(SurfaceHolder surfaceHolder){
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
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;
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
    	finish();
	}
	@Override
	public void myStartActivity(Intent intent){
		startActivity(intent);
	}
	public void handleDecode(Result obj, Bitmap barcode) {
		inactivityTimer.onActivity();
//		playBeepSoundAndVibrate();
		showResult(obj, barcode);
	}

	private void showResult(final Result rawResult, Bitmap barcode) {
		String url = rawResult.getText();
		//url = "http://edu.ibotn.com/download/test/孟母三迁_孟母三迁_1080P在线观看_腾讯视频.mp4";
        //url = "http://edu.ibotn.com/download/test/1516343632429.wav";
		//url = "http://edu.ibotn.com/download/test/大王叫我来巡山-贾乃亮,甜馨.mp3";
		//url = "http://edu.ibotn.com/download/test/kq151511779435544477.png";
		//url = "http://edu.ibotn.com/download/test/test_pic_3.jpg";

		Log.i("jlzou", "result--> " + url);
		restartPreviewAfterDelay(200);
//		WebViewJsJavaCallEachOtherActivity.showQrResult(url);
//		finish();
	}

	public void restartPreviewAfterDelay(long delayMS) {
		if (handler != null) {
			handler.sendEmptyMessageDelayed(MessageIDs.restart_preview, delayMS);
		}
	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			try {
				AssetFileDescriptor fileDescriptor = getAssets().openFd("qrbeep.ogg");
				this.mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(),
						fileDescriptor.getLength());
				this.mediaPlayer.setVolume(0.1F, 0.1F);
				this.mediaPlayer.prepare();
			} catch (IOException e) {
				this.mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			setResult(RESULT_CANCELED);
			finish();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.back:
				finish();
				break;
		}
	}

}