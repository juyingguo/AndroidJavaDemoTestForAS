package com.sp.spmultipleapp.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.sp.spmultipleapp.R;

public class HandlerTestActivity extends Activity {
    private String TAG = HandlerTestActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);

//        testHandler();
    }

    public void clickStaticHandlerTestActivity(View view) {
        startActivity(new Intent(this, StaticHandlerTestActivity.class));
    }

    public void clickStaticHandlerProgressBarTestActivity(View view) {
        startActivity(new Intent(this, StaticHandlerProgressTestActivity.class));
    }

    private class H extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    final H mH = new H();
    final Handler getHandler() {
        return mH;
    }
    private void testHandler() {
        Log.d(TAG,"testHandler");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = getHandler();
            }
        }).start();

    }
}
