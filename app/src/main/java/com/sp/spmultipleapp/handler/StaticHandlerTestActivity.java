package com.sp.spmultipleapp.handler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.sp.spmultipleapp.R;

import java.lang.ref.WeakReference;

public class StaticHandlerTestActivity extends Activity {
    private final static String TAG = StaticHandlerTestActivity.class.getSimpleName();
//    @SuppressLint("HandlerLeak")
//    static Handler mHandlerInner = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//        }
//    };
    MyStaticInnerHandler mHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_handler_test);

//        mHandlerInner.sendEmptyMessageDelayed(0, 5*60 * 1000);
        mHandler = new MyStaticInnerHandler(this);
        mHandler.sendEmptyMessageDelayed(0, 5*60 * 1000);
    }
    private static class MyStaticInnerHandler extends Handler {
        private WeakReference<StaticHandlerTestActivity> weakReference;
        private StaticHandlerTestActivity activity;

        private MyStaticInnerHandler(StaticHandlerTestActivity activity) {
            weakReference = new WeakReference<>(activity);
            this.activity = activity;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage,msg.what: " + msg.what);
            StaticHandlerTestActivity activity = weakReference.get();
            switch (msg.what) {
                case 0:
                    Log.d(TAG, "handleMessage,activity: " + activity);
                    break;
            }
        }
    }
}
