package com.sp.spmultipleapp.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.sp.spmultipleapp.R;
import com.sp.spmultipleapp.customview.QRCodeScanDialog;
import com.sp.spmultipleapp.customview.QRCodeScanDialogNotCanceled;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * PopupWindow 测试：测试PopupWindow弹出时，PopupWindow外部支持点击事件，且PopupWindow不消失。
 */
public class DialogTestActivity extends AppCompatActivity {
    private static final String TAG = "DialogTestActivity";

    Unbinder butterKnifeBind = null;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }
    public void clickShowTip(View view) {
        Toast.makeText(this,"behind show",Toast.LENGTH_SHORT).show();
    }
    public void clickShowQRCodeScanDialog(View view) {
        QRCodeScanDialog dialog = new QRCodeScanDialog(this);
        dialog.showAtLocationInActivity();
    }

    public void clickShowQRCodeScanDialogNotCanceled(View view) {
        QRCodeScanDialogNotCanceled dialogNotCanceled = new QRCodeScanDialogNotCanceled(this);
        dialogNotCanceled.showAtLocationInActivity();
//        WindowManager wm = getWindowManager();
//        int width = getResources().getDisplayMetrics().widthPixels / 3;
//        int heigh = getResources().getDisplayMetrics().heightPixels / 4 * 3;
//        WindowManager.LayoutParams param= new WindowManager.LayoutParams(width,heigh);
//        param.width = width;
//        param.height = heigh;
//        param.gravity = Gravity.TOP | Gravity.LEFT;
//        param.y = /*Utils.dp2px(100)*/100;
//        param.x = /*Utils.dp2px(100)*/100;
//        param.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
//        param.format = PixelFormat.RGBA_8888;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            param.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
//        } else {
//            param.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        }
//        wm.addView(dialogNotCanceled,param);
    }
}
