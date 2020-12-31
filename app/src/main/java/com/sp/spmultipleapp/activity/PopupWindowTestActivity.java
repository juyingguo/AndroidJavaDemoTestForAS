package com.sp.spmultipleapp.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.sp.spmultipleapp.R;
import com.sp.spmultipleapp.application.CoreApplication;
import com.sp.spmultipleapp.customview.QRCodeScanDialog;
import com.sp.spmultipleapp.customview.QRCodeScanPopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * PopupWindow 测试：测试PopupWindow弹出时，PopupWindow外部支持点击事件，且PopupWindow不消失。
 */
public class PopupWindowTestActivity extends AppCompatActivity {
    private static final String TAG = "PopupWindowTestActivity";

    Unbinder butterKnifeBind = null;

    private Context mContext;
    @BindView(R.id.btn_two)
    Button btnTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

    }
    private void showActivityTypePopupWindow() {

        View contentView = View.inflate(mContext,R.layout.popupwindow_test,null);
        //构造函数设置接收焦点
        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //获取控件宽度
        int relativeWidth = btnTwo .getWidth(); // TODO: 2018/11/21
        popupWindow.setWidth(relativeWidth*2);
//        popupWindow.setTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ff00")));
//        popupWindow.setBackgroundDrawable(null);
//        popupWindow.setOutsideTouchable(true);

        Log.d(TAG,"showActivityTypePopupWindow,popupWindow.isFocusable():" + popupWindow.isFocusable());
        popupWindow.setFocusable(false);
//        popupWindow.showAsDropDown(btnTwo, 0, 0, Gravity.BOTTOM);//或者指定显示在控件下方。
        int[] location = new int[2];
        View currentRootView = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        currentRootView.getLocationOnScreen(location);
        Log.d(TAG,"showActivityTypePopupWindow,currentRootView x:" + location[0] + ",y:"+ location[1]);
        popupWindow.showAtLocation(currentRootView,Gravity.NO_GRAVITY,location[0] - 100,location[1] + 200);
        contentView.findViewById(R.id.btn_in_popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopupWindowTestActivity.this,"popup in show",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }
    public void clickShowTip(View view) {
        Toast.makeText(this,"behind show",Toast.LENGTH_SHORT).show();
    }
    public void clickShowPopupWindow(View view) {
        showActivityTypePopupWindow();
    }


    public void clickShowQRCodeScanPopupWindow(View view) {
        Log.d(TAG,"clickShowQRCodeScanPopupWindow.");
        QRCodeScanPopupWindow window = new QRCodeScanPopupWindow(this
                , View.inflate(this, R.layout.popupwindow_qr_code_scan,null));
        window.showAtLocationInActivity();
    }

}
