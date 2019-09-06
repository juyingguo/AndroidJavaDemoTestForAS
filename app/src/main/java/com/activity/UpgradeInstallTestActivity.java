package com.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.sp.spmultipleapp.R;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import util.FileUtils;
import util.LogUtil;

public class UpgradeInstallTestActivity extends AppCompatActivity {
    private String TAG = UpgradeInstallTestActivity.class.getSimpleName();
    Unbinder butterKnifeBind = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_install_test);

        butterKnifeBind = ButterKnife.bind(this);
    }

    @OnClick({R.id.button_install})
    public void clickView(View view){
        if (view.getId() == R.id.button_install){

            installApk();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (butterKnifeBind != null){
            butterKnifeBind.unbind();

        }
    }


    public void installApk() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            String installDir = Environment.getExternalStorageDirectory().getAbsoluteFile() + File.separator + "install_test";
            String filePath = installDir + File.separator + "app-debug.apk";
            File file = new File(filePath);
            LogUtil.d(TAG, "installApk filePath = " + filePath);
            Intent installIntent = new Intent();
            installIntent.setAction(Intent.ACTION_VIEW);
            //判读版本是否在7.0~8.0，如果是则不加FLAG_ACTIVITY_NEW_TASK，否则强制加FLAG_ACTIVITY_NEW_TASK
            LogUtil.i(TAG, "Build.VERSION.SDK_INT: " + Build.VERSION.SDK_INT);
           /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                Uri apkUri = FileProvider.getUriForFile(this, "com.ibotn.newapp.fileprovider", file);
                installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            }
            else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                Uri apkUri = FileProvider.getUriForFile(this, "com.ibotn.newapp.fileprovider", file);
                installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
            }*/
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                installIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");

            try {
                startActivity(installIntent);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }

    }
}
