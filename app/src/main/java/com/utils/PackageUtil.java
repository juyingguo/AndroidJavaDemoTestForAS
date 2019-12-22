package com.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Date:2019/10/30,9:57
 * author:jy
 */
public class PackageUtil {
    private static final String TAG = "PackageUtil";
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
//            e.printStackTrace();//ignore
            return false;
        }
    }
    public static boolean isApkInstalled(Context context, String packageName ){
        if (context == null){
            throw new NullPointerException("context can not be null");
        }
        final PackageManager packageManager = context.getPackageManager();

        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for ( int i = 0; i < pinfo.size(); i++ )
        {
            // 循环判断是否存在指定包名
            if(pinfo.get(i).packageName.equalsIgnoreCase(packageName)){
                return true;
            }
        }
        return false;
    }
    public static boolean startApk(Context context, String packageName){
        if (context == null){
            throw new NullPointerException("context can not be null");
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        if(intent == null){
            Toast.makeText(context, "未安装", Toast.LENGTH_LONG).show();
            return false;
        }else{
            context.startActivity(intent);
            return true;
        }
    }
    public static void startApk(Context context,String packageName, String classStr) {
        if (context == null){
            throw new NullPointerException("context can not be null");
        }
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, classStr));
            Log.d(TAG,"startApk,packageName:" + packageName);
            ComponentName componentName = intent.resolveActivity(context.getPackageManager());
            Log.d(TAG,"startApk,componentName:" + componentName);
            if (componentName != null){
                context.startActivity(intent);
            }
        } catch (Exception e) {
//            ToastUtil.showCustomToast(getString(R.string.str_err_fun_opening));
            e.printStackTrace();
        }
    }
}
