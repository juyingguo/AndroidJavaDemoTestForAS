package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.sp.spmultipleapp.R;

import com.utils.DensityUtil;

public class LinearLayoutTestActivity extends Activity {
    String TAG = LinearLayoutTestActivity.class.getSimpleName();
    LinearLayout linearLayout;
    LinearLayout linearLayout12;
    LinearLayout linearLayout13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"onCreate");

        setContentView(R.layout.activity_linear_layout_test);

        linearLayout = findViewById(R.id.linearLayout);
        linearLayout12 = findViewById(R.id.linearLayout2);
        linearLayout13 = findViewById(R.id.linearLayout3);
//        linearLayout.measure();
//        linearLayout.

        printDpPx();

        printLinearLayout(linearLayout);

        printLinearLayout(linearLayout12);
        printLinearLayout(linearLayout13);
    }

    private void printDpPx() {


        Log.d(TAG,"printDpPx 200dp to px :" + DensityUtil.dip2px(this,200));
        Log.d(TAG,"printDpPx  200px to dp :" + DensityUtil.px2dip(this,200));
    }

    private void printLinearLayout(LinearLayout linearLayout) {

        Log.d(TAG,"printLinearLayout，start linearLayout:" + linearLayout);
//measure方法的参数值都设为0即可
        linearLayout.measure(0,0);
//获取组件的宽度
        int width = linearLayout.getMeasuredWidth();
//获取组件的高度
        int height = linearLayout.getMeasuredHeight();

        Log.d(TAG,"linearLayout.getMeasuredWidth()：" + width);
        Log.d(TAG,"linearLayout.getMeasuredHeight()：" + height);

        Log.d(TAG,"linearLayout.getWidth()：" + linearLayout.getWidth());
        Log.d(TAG,"linearLayout.getHeight()：" + linearLayout.getHeight());


        //如果想直接获取在布局文件中定义的组件的宽度和高度，可以直接使用View.getLayoutParams().width和View.getLayoutParams().height
        Log.d(TAG,"linearLayout.getLayoutParams().width：" + linearLayout.getLayoutParams().width);
        Log.d(TAG,"linearLayout.getLayoutParams().height：" + linearLayout.getLayoutParams().height);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //在调用这两个方法之前，必须调用View.measure方法先测量组件的宽度和高度。
        //measure方法的参数值都设为0即可

        printLinearLayout(linearLayout);

        printLinearLayout(linearLayout12);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }
}
