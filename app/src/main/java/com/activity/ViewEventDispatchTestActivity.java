package com.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.customview.MyButton;
import com.customview.MyLinearLayout;
import com.sp.spmultipleapp.R;

public class ViewEventDispatchTestActivity extends AppCompatActivity {

    private static final String TAG = ViewEventDispatchTestActivity.class.getSimpleName();

    MyLinearLayout myLinearLayout;
    MyButton myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView01();

        setContentView02();
    }

    /**
     * 添加内容为控件对象。
     */
    private void setContentView02() {
        MyLinearLayout linearLayout = new MyLinearLayout(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
        linearLayout.setBackgroundColor(Color.parseColor("#00C5CD"));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(layoutParams);
        setContentView(linearLayout);
    }

    private void setContentView01() {
        setContentView(R.layout.activity_view_event_dispatch_test);

        myLinearLayout = findViewById(R.id.myLinearLayout);
        myButton = findViewById(R.id.myButton);

        Log.e(TAG, "onCreate myLinearLayout.isClickable():" + myLinearLayout.isClickable());
        Log.e(TAG, "onCreate myButton.isClickable():" + myButton.isClickable());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent ACTION_DOWN");

                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent ACTION_UP");
                break;

            default:
                break;
        }

        boolean superOnTouchEvent = super.onTouchEvent(event);
        Log.e(TAG, "superOnTouchEvent:" + superOnTouchEvent);
        return superOnTouchEvent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent ACTION_UP");
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
