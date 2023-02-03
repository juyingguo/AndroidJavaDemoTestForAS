package com.sp.spmultipleapp.handler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sp.spmultipleapp.R;

import java.lang.ref.WeakReference;

public class StaticHandlerProgressTestActivity extends Activity {
    private final static String TAG = StaticHandlerProgressTestActivity.class.getSimpleName();

    private TextView mTextView;
    MyStaticInnerHandler mHandler = null;
    private WeakReference<StaticHandlerProgressTestActivity> weakRefActivity;
    private boolean activityAliveFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_handler_progress_test);
        mTextView = findViewById(R.id.textView);
        weakRefActivity = new WeakReference<>(this);
        mHandler = new MyStaticInnerHandler(weakRefActivity);
        mHandler.sendEmptyMessageDelayed(0, 10 * 1000);
    }

    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < 100 &&  activityAliveFlag) {
                    try {
                        Message message = new Message();
                        progress = progress + 1;
                        message.arg1 = progress;
                        mHandler.sendMessage(message);
                        Thread.sleep(100); //不能在主线程中使用
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private static class MyStaticInnerHandler extends Handler {

        private WeakReference<StaticHandlerProgressTestActivity> activity;

        private MyStaticInnerHandler(WeakReference<StaticHandlerProgressTestActivity> activity) {
            this.activity = activity;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(activity.get() == null){
                return;
            }
            Log.d(TAG, "handleMessage,msg.what: " + msg.what + ",msg.arg1:" + msg.arg1);
            //在静态内部类/匿名类中使用外部类的属性或方法时，可以显示的持有一个弱引用
            final StaticHandlerProgressTestActivity activity = this.activity.get(); //弱引用
            activity.mTextView.setText(msg.arg1 + "%");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityAliveFlag = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityAliveFlag = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
