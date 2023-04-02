package com.sp.spmultipleapp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sp.spmultipleapp.R;

/**
 *
 */
public class ProximityScreenOffMockInCallTest extends AppCompatActivity implements SensorEventListener {
    private String TAG = "ProximityScreenOffMockInCallTest";
    private PowerManager.WakeLock mWakeLock;
    private Sensor mSensor;
    private SensorManager mSensorManager;
    private PowerManager mPowerManager;
    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_screen_off_mock_in_call_test);

//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

//        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        //息屏设置
        mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK,
                "proximity_screen_off_wake_lock_test");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
        //注册传感器,先判断有没有传感器
//        if (mSensor != null)
//            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        if (!mWakeLock.isHeld())
            mWakeLock.acquire();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //传感器取消监听
//        mSensorManager.unregisterListener(this);
        //释放息屏
        if (mWakeLock.isHeld())
            mWakeLock.release();
        mWakeLock = null;
        mPowerManager = null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG,"onSensorChanged,event.values[0]:" + event.values[0]);
        if (event.values[0] <= 1.0) {
            //贴近手机
            //设置免提
//            audioManager.setSpeakerphoneOn(false);
//            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
            //关闭屏幕
//            if (!mWakeLock.isHeld())
//                mWakeLock.acquire();

        } else {
            //离开手机
//            audioManager.setMode(AudioManager.MODE_NORMAL);
//            //设置免提
//            audioManager.setSpeakerphoneOn(true);

            //唤醒设备
//            if (mWakeLock.isHeld())
//                mWakeLock.release();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
