package com.sp.spmultipleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class TouchViewTestActivity extends Activity {

    private static final String TAG = TouchViewTestActivity.class.getSimpleName();
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_view_test);
        button = (Button) findViewById(R.id.btn);
        
        registerListener();
    }

    private void registerListener() {
        
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.w(TAG,"onTouch>>getAction:" + event.getAction() + ",getActionMasked:" + event.getActionMasked() + ",getActionIndex:" + event.getActionIndex());


                return true;
            }
        });
        
        
    }

    public void toucTest(View view) {

        
        
    }
}
