package com.sp.spmultipleapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FilenameFilter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FileExploreActivity extends Activity {
    private String TAG = FileExploreActivity.class.getSimpleName();
    Unbinder butterKnifeBind = null;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explore);

        mContext = this;
        butterKnifeBind = ButterKnife.bind(this);

        testFileFilter();

    }

    private void testFileFilter() {
        Log.d(TAG,"testFileFilter()>>");

        File file = new File("/storage/sd-ext");
        File[] fList = null;
        if (file.exists() && file.isDirectory()){

            fList = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    Log.d(TAG,"testFileFilter()>>dir:" + dir.getAbsolutePath()) ;
                    Log.d(TAG,"testFileFilter()>>filename:" + filename) ;
                    return true;
                }
            });
        }
    }

    @OnClick({R.id.tv_uhost,R.id.tv_dir_open_to_user_and_uhost})
    public void clickView(View view){
        if (view.getId() == R.id.tv_uhost){
            Intent intent = getPackageManager().getLaunchIntentForPackage(Constant.ThirdPartAppPackageName.OTHER_APK_PACKAGENAME_FILE_EXPLORER);
            if (intent != null && intent.resolveActivity(getPackageManager()) != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constant.EXTRA_START_APP_FLAG, Constant.START_BY_IBOTNCLOUDPLAYER);//启动FileExplorer,FileExployer根据这个标志设置可以操作的控件
                startActivity(intent);
            }
        }else if (view.getId() == R.id.tv_dir_open_to_user_and_uhost){
            Intent intent = getPackageManager().getLaunchIntentForPackage(Constant.ThirdPartAppPackageName.OTHER_APK_PACKAGENAME_FILE_EXPLORER);
            if (intent != null && intent.resolveActivity(getPackageManager()) != null) {
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(Constant.EXTRA_START_APP_FLAG, Constant.START_BY_IBOTNCLOUDPLAYER);//启动FileExplorer,FileExployer根据这个标志设置可以操作的控件
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnifeBind.unbind();
    }
}
