package com.sp.spmultipleapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
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
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
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
        QRCodeScanDialogOutsideClick dialogNotCanceled = new QRCodeScanDialogOutsideClick(this);
        dialogNotCanceled.showAtLocationInActivity();
    }
}
