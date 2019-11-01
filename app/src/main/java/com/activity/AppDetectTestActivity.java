package com.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.sp.spmultipleapp.R;
import com.utils.PackageUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AppDetectTestActivity extends AppCompatActivity {
    private static final String TAG = "AppDetectTestActivity";

    Unbinder butterKnifeBind = null;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detect_test);
        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

    }

    @OnClick({
            R.id.btn_app_detect
            ,R.id.btn_app_start


    })
    public void clickView(View view) {
        if (view.getId() == R.id.btn_app_detect){
            boolean checkApkExist = checkApkExist(this, "air.com.wmtc.dsxb");
            Log.d(TAG,"checkApkExist[air.com.wmtc.dsxb]:" + checkApkExist);
            checkApkExist = checkApkExist(this, "air.com.wmtc.dszb");
            Log.d(TAG,"checkApkExist[air.com.wmtc.dszb]:" + checkApkExist);
            checkApkExist = PackageUtil.isApkInstalled(this, "air.com.wmtc.dsxb");
            Log.d(TAG,"checkApkExist[air.com.wmtc.dsxb],isApkInstalled:" + checkApkExist);
            checkApkExist = PackageUtil.isApkInstalled(this, "air.com.wmtc.dszb");
            Log.d(TAG,"checkApkExist[air.com.wmtc.dszb],isApkInstalled:" + checkApkExist);

            boolean startAak = PackageUtil.startApk(this, "air.com.wmtc.dsxb");
            Log.d(TAG,"checkApkExist[air.com.wmtc.dsxb],startAak:" + startAak);

            PackageUtil.startApk(this,"air.com.wmtc.dsxb","air.com.wmtc.dsxb.AppEntry");
        }else if (view.getId() == R.id.btn_app_start){
          /*  boolean startAak = PackageUtil.startApk(this, "air.com.wmtc.dszb");
            Log.d(TAG,"checkApkExist[air.com.wmtc.dszb],startAak:" + startAak);*/

            PackageUtil.startApk(this,"air.com.wmtc.dszb","air.com.wmtc.dszb.AppEntry");


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }

    /**
     *
     * @param context Context
     * @param packageName app packageName
     * @return boolean
     */
    public boolean checkApkExist(Context context, String packageName) {
        if (context == null){
            throw new NullPointerException("context can not be null");
        }
        Log.d(TAG,"checkApkExist,packageName:" + packageName);
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            Log.d(TAG,"checkApkExist,packageName:" + packageName + ",packageInfo:" + packageInfo);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     *
     * @param context Context
     * @param packageName 主包名 例如com.test.example
     * @return
     */
    public boolean checkApkExist02(Context context, String packageName) {
        if (context == null){
            throw new NullPointerException("context can not be null");
        }
        Log.d(TAG,"checkApkExist,packageName:" + packageName);
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            PackageInfo packageInfo = null;
            Log.d(TAG,"checkApkExist,Build.VERSION.SDK_INT :" + Build.VERSION.SDK_INT );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){

                packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES);
            }else {
                packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);

            }
            Log.d(TAG,"checkApkExist,packageName:" + packageName + ",packageInfo:" + packageInfo);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
/*————————————————
    版权声明：本文为CSDN博主「LihaCheng」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/cheng900716lihai/article/details/52371302*/
}
