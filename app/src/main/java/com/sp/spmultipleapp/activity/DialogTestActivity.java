package com.sp.spmultipleapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.sp.spmultipleapp.R;
import com.sp.spmultipleapp.customview.QRCodeScanDialog;
import com.sp.spmultipleapp.customview.QRCodeScanDialogOutsideClick;

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
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏,需要当前activity继承AppCompatActivity
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dialog_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

        hideNavigationBar();
    }
    private void hideNavigationBar(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
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
        QRCodeScanDialogOutsideClick dialogNotCanceled = new QRCodeScanDialogOutsideClick(this);
        dialogNotCanceled.showAtLocationInActivity();
    }

    public void clickToFullscreen(View view) {
        hideNavigationBar();
    }
}
