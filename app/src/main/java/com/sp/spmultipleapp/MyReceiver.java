package com.sp.spmultipleapp;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by 123 on 2018/4/8.
 */

public class MyReceiver extends BroadcastReceiver {
    private String TAG = MyReceiver.class.getSimpleName();
    public static  String ACTION_TEST_ABC = "action_test_abc";
    Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        String action = intent.getAction();
        Log.d(TAG,"onReceive>>action:" + action);
        if (TextUtils.equals(Intent.ACTION_BOOT_COMPLETED,action)){
            showDialog();
        }

    }
    /**
     *
     */
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setTitle("收到开机广播")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog dialog = builder.create();
        //service如何弹出dialog : 需要在show方法之前添加TYPE_SYSTEM_ALERT，表示该dialog是一个系统的dialog
        dialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
